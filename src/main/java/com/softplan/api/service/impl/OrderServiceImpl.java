package com.softplan.api.service.impl;

import com.softplan.api.domain.Customer;
import com.softplan.api.domain.Equipament;
import com.softplan.api.domain.Note;
import com.softplan.api.domain.Order;
import com.softplan.api.dto.request.OrderRequestDTO;
import com.softplan.api.dto.request.OrderStatusRequest;
import com.softplan.api.dto.response.OrderResponseDTO;
import com.softplan.api.dto.request.RegisterNoteRequestDTO;
import com.softplan.api.dto.complement.OrderDTO;
import com.softplan.api.dto.complement.StatusEnum;
import com.softplan.api.exception.ApiException;
import com.softplan.api.repository.CustomerRepository;
import com.softplan.api.repository.EquipamentRepository;
import com.softplan.api.repository.NoteRepositoy;
import com.softplan.api.repository.OrderRepository;
import com.softplan.api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private  MessageSource messageSource;

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private EquipamentRepository equipamentRepository;
    @Autowired
    private NoteRepositoy noteRepositoy;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public OrderResponseDTO createOrderServico(OrderRequestDTO request) {
        Customer customer = new Customer(request);
        Equipament equipament = new Equipament(request);
        Order order = new Order(request, StatusEnum.PENDING.name());
        customerRepository.save(customer);
        equipamentRepository.save(equipament);
        order.setCustomer(customer);
        order.setEquipament(equipament);
        orderRepository.save(order);
        OrderResponseDTO response = new OrderResponseDTO();
        response.setId(order.getId());
        return response;
    }

    @Override
    public OrderResponseDTO consultOrderPending() {
        List<Order> ordens = orderRepository.findByEndDateIsNull();
        if(ordens != null || ordens.isEmpty()){
            return new OrderResponseDTO(ordens);
        }

        return null;
    }

    @Override
    @Transactional
    public OrderDTO closeOrder(OrderStatusRequest request) throws ApiException {
        Order order = orderRepository.findById(request.getOrderNumber()).orElse(null);
        if(order != null){
            if(order.getStatus() != StatusEnum.CLOSE.name()){
                order.setEndDate(new Date());
                order.setStatus(StatusEnum.CLOSE.name());
                Note note = new Note("Close order ",new Date(), new Date(), order, request.getTechnicalName());
                orderRepository.save(order);
                noteRepositoy.save(note);
            }else{
                throw new ApiException(messageSource.getMessage("order.close", null, LocaleContextHolder.getLocale()));

            }
        }
        return new OrderDTO(order);
    }

    @Override
    @Transactional
    public OrderDTO startOrder(OrderStatusRequest request) throws ApiException {
        Order order = orderRepository.findById(request.getOrderNumber()).orElse(null);
        if(order != null){
            if(order.getStatus() != StatusEnum.STARTED.name()
                    && order.getStatus() != StatusEnum.CLOSE.name()){
                order.setStarDate(new Date());
                order.setStatus(StatusEnum.STARTED.name());
                Note note = new Note("Start order",new Date(), new Date(), order, request.getTechnicalName());
                orderRepository.save(order);
                noteRepositoy.save(note);
            }else{
                throw new ApiException(messageSource.getMessage("order.in.process", null, LocaleContextHolder.getLocale()));
            }
        }else{
            throw new ApiException(messageSource.getMessage("order.not.found", null, LocaleContextHolder.getLocale()));
        }
        return new OrderDTO(order);
    }

    @Override
    public String registerNote(RegisterNoteRequestDTO request) throws ApiException {
        Order order = orderRepository.findById(request.getOrderNumber()).orElse(null);
        if(order != null) {
            if(StatusEnum.valueOf(order.getStatus()).equals(StatusEnum.CLOSE)){
                order.setEndDate(null);
            }
            order.setStatus(request.getStatus().name());
            Note note = new Note(request.getDescription(), new Date(), new Date(), order, request.getTechnicalName());
            note.setDescription(request.getDescription());
            note.setStarDate(new Date());
            note.setOrder(order);
            noteRepositoy.save(note);
            orderRepository.save(order);
        }else{
            throw new ApiException(messageSource.getMessage("order.not.found", null, LocaleContextHolder.getLocale()));
        }
        return messageSource.getMessage("register.note.sucess", null, LocaleContextHolder.getLocale());
    }

    @Override
    public OrderResponseDTO consultServiceOrder() {
        List<Order> orders = orderRepository.findAll();
        return new OrderResponseDTO(orders);
    }

    @Override
    public void deleteOrderService(Long orderNumber) throws ApiException{
        Order order = orderRepository.findById(orderNumber).orElse(null);
        if(order != null){
            customerRepository.delete(order.getCustomer());
            equipamentRepository.delete(order.getEquipament());
            noteRepositoy.deleteAll(order.getNotes());
            orderRepository.delete(order);
        }else{
            throw new ApiException(messageSource.getMessage("order.not.found", null, LocaleContextHolder.getLocale()));
        }
    }
}

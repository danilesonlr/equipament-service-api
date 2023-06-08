package com.softplan.api.service;

import com.softplan.api.dto.request.OrderRequestDTO;
import com.softplan.api.dto.request.OrderStatusRequest;
import com.softplan.api.dto.response.OrderResponseDTO;
import com.softplan.api.dto.request.RegisterNoteRequestDTO;
import com.softplan.api.dto.complement.OrderDTO;
import com.softplan.api.exception.ApiException;

public interface OrderService {
    /**
     * Create work order for damaged equipment
     * @param request
     * @return
     */
    OrderResponseDTO createOrderServico(OrderRequestDTO request);

    /**
     * Consult service order that has some pending
     * @param request
     * @return
     */
    OrderResponseDTO consultOrderPending();

    /**
     * Close completed order
     * @param request
     * @return
     */
    OrderDTO closeOrder(OrderStatusRequest request) throws ApiException;

    /**
     * Start service order fulfillment
     * @param request
     * @return
     */
    OrderDTO startOrder(OrderStatusRequest request) throws ApiException;

    /**
     * Register a note for a service order or add a pendency
     * @param request
     * @return
     */
    String registerNote(RegisterNoteRequestDTO request) throws ApiException;

    /**
     * Consult all open service orders
     *
     * @return
     */
    OrderResponseDTO consultServiceOrder();


    /**
     * Delete all order records
     * @param orderNumber
     */
    void deleteOrderService(Long orderNumber) throws ApiException;
}

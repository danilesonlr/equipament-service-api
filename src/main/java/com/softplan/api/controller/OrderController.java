package com.softplan.api.controller;

import com.softplan.api.dto.request.OrderRequestDTO;
import com.softplan.api.dto.request.OrderStatusRequest;
import com.softplan.api.dto.response.OrderResponseDTO;
import com.softplan.api.dto.request.RegisterNoteRequestDTO;
import com.softplan.api.exception.ApiException;
import com.softplan.api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/v1")
@Validated
public class OrderController {

    @Autowired
    OrderService service;

    @PostMapping(value="/createOrder",produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
    public ResponseEntity createOrderServico(@RequestBody @Valid OrderRequestDTO request, UriComponentsBuilder uriBuilder) {
        OrderResponseDTO dto = service.createOrderServico(request);
        URI uri = uriBuilder.path("/v1/createOrder/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping(value = "/consultOrderPendding", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
    public ResponseEntity consultOrderPending() {

        return ResponseEntity.ok(service.consultOrderPending());
    }

    @PutMapping(value="/closeOrder", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
    @Transactional
    public ResponseEntity closeOrder(@RequestBody @Valid OrderStatusRequest request) throws ApiException {
        return ResponseEntity.ok(service.closeOrder(request));
    }

    @PutMapping(value="/startOrder", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
    @Transactional
    public ResponseEntity startOrder(@RequestBody @Valid OrderStatusRequest request) throws ApiException {

        return ResponseEntity.ok(service.startOrder(request));
    }

    @PutMapping(value="/registerNote", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
    public ResponseEntity<String> registerNote(@RequestBody @Valid RegisterNoteRequestDTO request) throws ApiException {

        return ResponseEntity.ok(service.registerNote(request));
    }

    @GetMapping("/consultServiceOrder")
    @Transactional
    public ResponseEntity consultServiceOrder() {
        return ResponseEntity.ok(service.consultServiceOrder());
    }

    @DeleteMapping("/deleteOrderService/{orderNumber}")
    @Transactional
    public ResponseEntity deleteOrderService(@PathVariable Long orderNumber) throws ApiException {
        service.deleteOrderService(orderNumber);
        return ResponseEntity.noContent().build();
    }
}

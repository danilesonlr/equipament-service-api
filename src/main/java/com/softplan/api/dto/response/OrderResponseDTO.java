package com.softplan.api.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.softplan.api.domain.Order;
import com.softplan.api.dto.complement.OrderDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderResponseDTO implements Serializable {
    private Long id;
    private List<OrderDTO> orders;


    public OrderResponseDTO(List<Order> ordersEntity){
        List<OrderDTO> listRetorn = new ArrayList<>();
        ordersEntity.forEach(order -> {
            OrderDTO dto = new OrderDTO(order);
            listRetorn.add(dto);
        });
        this.orders = listRetorn;
    }
}

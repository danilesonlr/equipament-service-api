package com.softplan.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusRequest {
    @NotNull(message = "Campo obrigatório")
    private Long orderNumber;
    @NotNull(message = "Campo obrigatório")
    private String technicalName;
}

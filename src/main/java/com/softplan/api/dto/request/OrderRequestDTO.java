package com.softplan.api.dto.request;

import com.softplan.api.dto.complement.CustomerDTO;
import com.softplan.api.dto.complement.EquipamentDTO;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDTO {
    @NotNull(message = "Campo obrigatório")
    private String name;
    @NotNull(message = "Campo obrigatório")
    private String address;
    @NotNull(message = "Campo obrigatório")
    @Pattern(regexp = "\\d{11}", message = "O número de telefone fornecido é inválido")
    private String telephone;
    @NotNull(message = "Campo obrigatório")
    @Email(message = "Email fornecido inválido.")
    private String email;
    @NotNull(message = "Campo obrigatório")
    private String equipament;
    @NotNull(message = "Campo obrigatório")
    private String type;
    @NotNull(message = "Campo obrigatório")
    private String brand;
    @NotNull(message = "Campo obrigatório")
    private String problem;
}

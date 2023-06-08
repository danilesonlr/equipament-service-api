package com.softplan.api.dto.complement;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.softplan.api.domain.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDTO implements Serializable {
    private String name;
    private String address;
    @Pattern(regexp = "\\d{10}", message = "O número de telefone fornecido é inválido")
    private String telephone;
    @Email(message = "Email fornecido inválido.")
    private String email;

    public CustomerDTO(Customer entity){
        this.name = entity.getName();
        this.address = entity.getAddress();
        this.telephone = entity.getTelephone();
        this.email = entity.getEmail();
    }
}

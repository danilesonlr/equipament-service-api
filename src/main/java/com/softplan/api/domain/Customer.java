package com.softplan.api.domain;

import com.softplan.api.dto.request.OrderRequestDTO;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "tb_customer")
@Entity(name = "tb_customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Customer implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String name;
    private String address;
    private String telephone;
    private String email;

    public Customer(OrderRequestDTO dto){
        this.name = dto.getName();
        this.address =  dto.getAddress();
        this.email = dto.getEmail();
        this.telephone = dto.getTelephone();
    }
}

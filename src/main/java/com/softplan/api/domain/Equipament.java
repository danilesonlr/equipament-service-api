package com.softplan.api.domain;

import com.softplan.api.dto.request.OrderRequestDTO;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "tb_equipament")
@Entity(name = "tb_equipament")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Equipament implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String equipament;
    private String type;
    private String brand;

    public Equipament(OrderRequestDTO dto){
        this.equipament = dto.getEquipament();
        this.type = dto.getType();
        this.brand = dto.getBrand();
    }

}

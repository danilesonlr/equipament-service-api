package com.softplan.api.dto.complement;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.softplan.api.domain.Equipament;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EquipamentDTO implements Serializable {
    private String equipament;
    private String type;
    private String brand;

    public EquipamentDTO(Equipament equipament){
        this.equipament = equipament.getEquipament();
        this.type = equipament.getType();
        this.brand = equipament.getBrand();
    }
}

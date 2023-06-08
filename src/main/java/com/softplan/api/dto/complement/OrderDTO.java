package com.softplan.api.dto.complement;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.softplan.api.domain.Customer;
import com.softplan.api.domain.Equipament;
import com.softplan.api.domain.Note;
import com.softplan.api.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO implements Serializable {
    private Long id;
    private Date starDate;
    private Date endDate;
    private String problem;
    private StatusEnum status;
    private CustomerDTO customer;
    private EquipamentDTO equipament;
    private List<NoteDTO> notes;

    public OrderDTO(Order order) {
        if(order != null){
            this.id = order.getId();
            this.starDate = order.getStarDate();
            this.endDate = order.getEndDate();
            this.customer = new CustomerDTO(order.getCustomer());
            this.equipament = new EquipamentDTO(order.getEquipament());
            this.problem = order.getProblem();
            this.notes = parserNotes(order.getNotes());
            this.status = StatusEnum.valueOf(order.getStatus());
        }
    }

    private List<NoteDTO> parserNotes(List<Note> notes) {
        List<NoteDTO> listRetorn = new ArrayList<>();
        notes.forEach(note -> {
            NoteDTO dto = new NoteDTO(note);
            listRetorn.add(dto);
        });
        return listRetorn;
    }
}

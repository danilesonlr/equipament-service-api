package com.softplan.api.dto.complement;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.softplan.api.domain.Note;
import com.softplan.api.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NoteDTO implements Serializable {

    private String description;
    private Date starDate;

    private String technicalName;


    public NoteDTO(Note note){
        this.description =note.getDescription();
        this.starDate = note.getStarDate();
        this.technicalName = note.getTechnicalName();
    }
}

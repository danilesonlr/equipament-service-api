package com.softplan.api.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Table(name = "tb_note")
@Entity(name = "note")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Note implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @Column(name = "star_date")
    private Date starDate;
    @Column(name = "technical_name")
    private String technicalName;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "order_id" )
    private Order order;

    public Note(String description, Date starDate, Date endDate, Order order, String technicalName) {
        this.description = description;
        this.starDate = starDate;
        this.order = order;
        this.technicalName = technicalName;
    }
}

package com.softplan.api.domain;

import com.softplan.api.dto.request.OrderRequestDTO;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Table(name = "tb_order")
@Entity(name = "tb_order")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "start_date")
    private Date starDate;
    @Column(name = "end_date")
    private Date endDate;
    private String problem;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    private String status;
    @ManyToOne
    @JoinColumn(name = "equipament_id")
    private Equipament equipament;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Note> notes;

    public Order(OrderRequestDTO dto, String status){
        this.problem = dto.getProblem();
        this.status = status;
    }

}

package com.Ecommerce.order.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "orders")
public class Order {

    public  enum Status{
        PENDDING,
        PROCCESS,
        COMPLETED,
        CANCELD
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long UserId;

    @OneToMany(mappedBy = "order")
    private List<OrderDetailes> detailes;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;


    @PrePersist
    protected void onCreate() {
        if(status==null){
            status = Status.valueOf("PENDDING");
        }

    }

}

package com.Ecommerce.order.model.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Builder
public class OrderDetailes {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "order_id",nullable = false,foreignKey = @ForeignKey(name = "order_id"))
    private  Order order;

    private Long ProductId;
    private Integer count;
    private Integer price;
}

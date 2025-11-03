package com.Ecommerce.order.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderValidationMessage {
    List<Long> product_ids;
    List<Integer> counts;
    Integer    price;
    Long       user_id;
    Boolean    product_valied = true;
    Long       order_id;
    Boolean    wallet_valied = true;
}

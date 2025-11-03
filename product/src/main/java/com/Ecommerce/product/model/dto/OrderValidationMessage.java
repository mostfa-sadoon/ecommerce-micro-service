package com.Ecommerce.product.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class OrderValidationMessage {
    List<Long>    product_ids;
    List<Integer> counts;
    Integer       price;
    Long          user_id;
    Boolean       product_valied;
    Long          order_id;
}

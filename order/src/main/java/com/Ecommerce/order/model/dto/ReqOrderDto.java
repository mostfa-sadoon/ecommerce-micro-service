package com.Ecommerce.order.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqOrderDto {
    private Long user_id;
    List<ProductDto> products;
}

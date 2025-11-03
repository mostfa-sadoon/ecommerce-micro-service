package com.Ecommerce.order.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDto {
   private Long product_id;
   private Integer count;
   private Integer price;
}

package com.Ecommerce.product.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

  Integer product_id;
  Integer count;
  Integer price;

}

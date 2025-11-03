package com.Ecommerce.product.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResProductDto {

    private Long id;
    private String name;
    private Integer stock;
    private Integer price;
}

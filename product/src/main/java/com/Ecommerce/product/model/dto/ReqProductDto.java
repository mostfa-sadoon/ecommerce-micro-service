package com.Ecommerce.product.model.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReqProductDto {
    private Long id;
    @NonNull()
    private String name;
    @NonNull()
    private Integer stock;
    @NonNull()
    private Integer price;
}

package com.Ecommerce.product.model.mapper;

import com.Ecommerce.product.model.dto.ReqProductDto;
import com.Ecommerce.product.model.dto.ResProductDto;
import com.Ecommerce.product.model.entity.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public Product dtoToEntity(ReqProductDto dto){
        return Product.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .stock(dto.getStock())
                .reserved(0)
                .build();
    }

    public ResProductDto entityToDto(Product entity){
        return ResProductDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice())
                .stock(entity.getStock())
                .build();
    }
}

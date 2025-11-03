package com.Ecommerce.product.service;


import com.Ecommerce.product.model.dto.ReqProductDto;
import com.Ecommerce.product.model.dto.ResProductDto;
import com.Ecommerce.product.model.entity.Product;
import com.Ecommerce.product.model.mapper.ProductMapper;
import com.Ecommerce.product.repository.ProductServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductServiceInterface productServiceInterface;

    @Autowired
    ProductMapper productMapper;

    public ResProductDto save(ReqProductDto dto){
        Product product = productMapper.dtoToEntity(dto);
        Product productsave = productServiceInterface.save(product);
        return productMapper.entityToDto(productsave);

    }

    public List<Product> getProducts(){
      return   productServiceInterface.findAll();
    }

    public Optional<Product> getProduct(Long id){
        return productServiceInterface.findById(id);
    }


}

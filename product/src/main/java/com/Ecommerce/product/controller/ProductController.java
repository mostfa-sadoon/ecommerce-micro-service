package com.Ecommerce.product.controller;

import com.Ecommerce.product.model.dto.ReqProductDto;
import com.Ecommerce.product.model.dto.ResProductDto;
import com.Ecommerce.product.model.entity.Product;
import com.Ecommerce.product.service.ProductService;
import com.Ecommerce.product.utility.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@Slf4j
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("save")
    public ResponseEntity<ApiResponse<ResProductDto>> save(@RequestBody ReqProductDto dto){
        System.out.println(dto.getName());
        ResProductDto data =  productService.save(dto);
        ApiResponse<ResProductDto> response = new ApiResponse<>("return product success",data);
        return  ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @GetMapping("/get/products")
    public   ResponseEntity<ApiResponse<List<Product>>>  getProducts(){
        List<Product> products = productService.getProducts();
        ApiResponse<List<Product>> response = new ApiResponse<>("return product success",products);
        return  ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @GetMapping("/get/product")
    public ResponseEntity<ApiResponse<Product>> getProduct(@RequestParam Long id){
        Product product =  productService.getProduct(id).get();
        ApiResponse<Product> response = new ApiResponse<>("return product success",product);
        return  ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

}

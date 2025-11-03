package com.Ecommerce.order.controller;


import com.Ecommerce.order.model.dto.ReqOrderDto;
import com.Ecommerce.order.model.entity.Order;
import com.Ecommerce.order.service.OrderService;
import com.Ecommerce.order.utility.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class orderController {

    @Autowired
    OrderService orderService;

    @PostMapping("save")
    public ResponseEntity<ApiResponse<Order>> save(@RequestBody ReqOrderDto dto){
      Order order =   orderService.save(dto);
      ApiResponse<Order> response = new ApiResponse<>(
              "order created successfuly",
                        order
        );
      return  ResponseEntity.status(HttpStatus.CREATED).body(response);
    }



}

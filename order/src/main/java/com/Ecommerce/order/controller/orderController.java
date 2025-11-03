package com.Ecommerce.order.controller;


import com.Ecommerce.order.model.dto.ReqOrderDto;
import com.Ecommerce.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class orderController {

    @Autowired
    OrderService orderService;

    @PostMapping("save")
    public void save(@RequestBody ReqOrderDto dto){
        orderService.save(dto);
    }



}

package com.Ecommerce.order.model.mapper;


import com.Ecommerce.order.model.dto.ReqOrderDto;
import com.Ecommerce.order.model.entity.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {

    public Order dtoToOrderEntity(ReqOrderDto dto){
         return  Order.builder()
                 .UserId(dto.getUser_id())
                 .build();
    }
}

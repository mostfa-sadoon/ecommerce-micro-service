package com.Ecommerce.order.service;


import com.Ecommerce.order.model.dto.OrderValidationMessage;
import com.Ecommerce.order.model.dto.ProductDto;
import com.Ecommerce.order.model.dto.ReqOrderDto;
import com.Ecommerce.order.model.entity.Order;
import com.Ecommerce.order.model.entity.OrderDetailes;
import com.Ecommerce.order.model.mapper.OrderMapper;
import com.Ecommerce.order.repository.DetailesRepository;
import com.Ecommerce.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class OrderService {

  @Autowired
  OrderRepository orderRepository;

  @Autowired
  OrderMapper orderMapper;

  @Autowired
  DetailesRepository detailesRepository;

  @Autowired
  OrderProduce orderProduce;

  @Transactional
  public  void save(ReqOrderDto dto){
      Order orderentity  = orderMapper.dtoToOrderEntity(dto);
      Order order =  orderRepository.save(orderentity);
      List<Long> products_id = new ArrayList<Long>();
      List<Integer> counts = new ArrayList<Integer>();
      OrderValidationMessage message = new OrderValidationMessage();
      Integer totalPrice = 0;
      for(ProductDto product : dto.getProducts()){
        products_id.add(product.getProduct_id());
        counts.add(product.getCount());
        message.setProduct_ids(products_id);
        totalPrice += product.getPrice();
        OrderDetailes detaile =  OrderDetailes.builder()
                  .count(product.getCount())
                  .price(product.getPrice())
                  .order(order)
                  .build();
          detailesRepository.save(detaile);
      }
      message.setPrice(totalPrice);
      message.setUser_id(dto.getUser_id());
      message.setCounts(counts);
      message.setOrder_id(order.getId());
      orderProduce.addOrder(message);
  }
}

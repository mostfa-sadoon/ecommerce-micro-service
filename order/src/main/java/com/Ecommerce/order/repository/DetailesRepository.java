package com.Ecommerce.order.repository;

import com.Ecommerce.order.model.entity.OrderDetailes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailesRepository extends JpaRepository<OrderDetailes,Long> {
}

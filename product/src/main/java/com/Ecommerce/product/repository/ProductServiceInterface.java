package com.Ecommerce.product.repository;

import com.Ecommerce.product.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductServiceInterface extends JpaRepository<Product,Long> {
}

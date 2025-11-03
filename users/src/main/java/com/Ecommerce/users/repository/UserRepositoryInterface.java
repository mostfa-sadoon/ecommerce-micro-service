package com.Ecommerce.users.repository;

import com.Ecommerce.users.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryInterface extends JpaRepository<User,Long> {
}

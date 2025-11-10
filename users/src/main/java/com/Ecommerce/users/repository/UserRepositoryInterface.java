package com.Ecommerce.users.repository;

import com.Ecommerce.users.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositoryInterface extends JpaRepository<User,Long> {

  public Optional<User>  findByUsername(String UserName);
  public Optional<User>  findByEmail(String Email);

}

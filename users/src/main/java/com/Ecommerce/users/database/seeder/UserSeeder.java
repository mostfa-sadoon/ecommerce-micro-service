package com.Ecommerce.users.database.seeder;


import com.Ecommerce.users.model.entity.User;
import com.Ecommerce.users.repository.UserRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


public class UserSeeder  {

   @Autowired
    UserRepositoryInterface userRepositoryInterface;

   @Transactional
    public  void run(String... args){
//          if(userRepositoryInterface.count() == 0){
//              User user = new User();
//              user.setName("mostafa");
//              user.setUsername("mostfa_sadoon");
//              user.setEmail("mostfa_sadoon");
//              user.setPassword("123456");
//              userRepositoryInterface.save(user);
//              System.out.println("✅  user seeded successfully!");
//          }

       System.out.println("✅  user seeded successfully!");
    }

}

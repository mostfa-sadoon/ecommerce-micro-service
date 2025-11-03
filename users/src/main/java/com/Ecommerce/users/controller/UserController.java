package com.Ecommerce.users.controller;


import com.Ecommerce.users.model.dto.req.UserReqDto;
import com.Ecommerce.users.model.dto.res.UserResDto;
import com.Ecommerce.users.model.entity.User;
import com.Ecommerce.users.service.imp.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {


   @Autowired
    UserService userService;

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userService.findUser(id);
    }

    @PostMapping("/save")
    public UserResDto createUser(@RequestBody UserReqDto user) {
        return userService.save(user);
    }


}

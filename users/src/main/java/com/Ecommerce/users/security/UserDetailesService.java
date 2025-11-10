package com.Ecommerce.users.security;


import com.Ecommerce.users.model.entity.User;
import com.Ecommerce.users.repository.UserRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailesService implements UserDetailsService {

    @Autowired
    UserRepositoryInterface userRepositoryInterface;

    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user  = this.userRepositoryInterface.findByUsername(name).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .build();
    }
}

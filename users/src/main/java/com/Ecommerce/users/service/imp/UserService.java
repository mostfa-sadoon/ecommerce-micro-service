package com.Ecommerce.users.service.imp;

import com.Ecommerce.users.excception.DuplicateResourceException;
import com.Ecommerce.users.model.dto.req.UserReqDto;
import com.Ecommerce.users.model.dto.res.UserResDto;
import com.Ecommerce.users.model.entity.User;
import com.Ecommerce.users.model.mapper.UserMapper;
import com.Ecommerce.users.repository.UserRepositoryInterface;
import com.Ecommerce.users.service.UserServiceInterface;
import com.Ecommerce.users.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    UserRepositoryInterface userRepositoryInterface;

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserProducer userProducer;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtil jwtUtil;

    public Optional<User> findUser(Long  id){
        return userRepositoryInterface.findById(id);
    }

    public UserResDto save(UserReqDto dto){

        // validate user name is unique
        if(userRepositoryInterface.findByUsername(dto.getUsername()).isPresent()){
            throw new DuplicateResourceException("username: Username is already taken");
        }

        // validate user name is unique
        if(userRepositoryInterface.findByEmail(dto.getEmail()).isPresent()){
            throw new DuplicateResourceException("email: Username is already taken");

        }

        User user = User.builder().username(dto.getUsername())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .name(dto.getName())
                .build();

        String token = jwtUtil.generateToken(dto.getUsername());

        userRepositoryInterface.save(user);
        System.out.println(user.getId());
        userProducer.addUser(user.getId());

        return UserResDto.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .name(user.getName())
                .token(token)
                .build();

    }
}

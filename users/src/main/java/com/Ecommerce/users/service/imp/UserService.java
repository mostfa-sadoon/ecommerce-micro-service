package com.Ecommerce.users.service.imp;

import com.Ecommerce.users.model.dto.req.UserReqDto;
import com.Ecommerce.users.model.dto.res.UserResDto;
import com.Ecommerce.users.model.entity.User;
import com.Ecommerce.users.model.mapper.UserMapper;
import com.Ecommerce.users.repository.UserRepositoryInterface;
import com.Ecommerce.users.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
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


    public Optional<User> findUser(Long  id){
        return userRepositoryInterface.findById(id);
    }

    public UserResDto save(UserReqDto dto){
        User user = User.builder().username(dto.getUsername())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .name(dto.getName())
                .build();
        userRepositoryInterface.save(user);
        System.out.println(user.getId());
        userProducer.addUser(user.getId());

        return UserResDto.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .name(user.getName())
                .build();

    }
}

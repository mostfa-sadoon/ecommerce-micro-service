package com.Ecommerce.users.model.mapper;

import com.Ecommerce.users.model.dto.req.UserReqDto;
import com.Ecommerce.users.model.dto.res.UserResDto;
import com.Ecommerce.users.model.entity.User;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class UserMapper {

   public User dtoToEntity(UserReqDto dto){
        return  User.builder().username(dto.getUsername())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .name(dto.getName())
                .build();
    }

  public   UserResDto EntityToResDto(User entity){
        return UserResDto.builder()
                .username(entity.getUsername())
                .email(entity.getEmail())
                .name(entity.getName())
                .build();
    }

}

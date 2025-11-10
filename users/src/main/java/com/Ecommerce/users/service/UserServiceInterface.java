package com.Ecommerce.users.service;


import com.Ecommerce.users.model.dto.req.UserReqDto;
import com.Ecommerce.users.model.dto.res.UserResDto;
import com.Ecommerce.users.model.entity.User;

import java.util.Optional;

public interface UserServiceInterface {
    UserResDto findUser(Long  id);
    UserResDto save(UserReqDto dto);
}

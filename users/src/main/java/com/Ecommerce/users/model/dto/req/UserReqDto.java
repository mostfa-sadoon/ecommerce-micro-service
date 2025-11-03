package com.Ecommerce.users.model.dto.req;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserReqDto {
    private String name;
    private String email;
    private String username;
    private String password;
}

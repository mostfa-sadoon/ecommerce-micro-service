package com.Ecommerce.users.model.dto.req;


import com.Ecommerce.users.repository.UserRepositoryInterface;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserReqDto {
    private String name;
    private String email;
    private String username;
    private String password;
}

package com.Ecommerce.users.controller;


import com.Ecommerce.users.model.dto.req.LoginRequest;
import com.Ecommerce.users.util.ApiResponse;
import com.Ecommerce.users.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtUtil;



    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Token>> login(@RequestBody LoginRequest dto){

        System.out.println("user name,"+dto.getUsername() + " - " + dto.getPassword());
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(),dto.getPassword())
        );
        String token = jwtUtil.generateToken(dto.getUsername());
        Token jwtTokwn = new Token();
        jwtTokwn.token = token;
            ApiResponse<Token>  response = new ApiResponse<Token>(
                "login success",
                jwtTokwn
        );
        return ResponseEntity.ok(response);
    }
}
class Token{
    public String token;
}

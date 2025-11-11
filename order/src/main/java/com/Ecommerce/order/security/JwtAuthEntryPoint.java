package com.Ecommerce.order.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {

    @Override
    public  void commence(HttpServletRequest request , HttpServletResponse response , AuthenticationException exception) throws IOException, ServletException {


        System.out.println("401 UNAUTHORIZED: " + exception.getMessage());

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write("{\"message\": \"Not authenticated or token invalid\"}");
    }
}

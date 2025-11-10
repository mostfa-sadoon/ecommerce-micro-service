package com.Ecommerce.users.excception;


import com.Ecommerce.users.util.ApiResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {


  @ExceptionHandler(DuplicateResourceException.class)
  public ResponseEntity<Map<String,Object>> handelUniqueException(DuplicateResourceException ex){
      Map<String, Object> response = new HashMap<>();

      // Create a list of error objects
      List<Map<String, String>> errors = new ArrayList<>();

      // Assuming your exception message is in the format "field: message"
      // Example: "username: Username is already taken"
      String[] parts = ex.getMessage().split(":", 2);
      String field = parts[0].trim();
      String message = parts.length > 1 ? parts[1].trim() : ex.getMessage();

      Map<String, String> error = new HashMap<>();
      error.put("field", field);
      error.put("message", message);
      errors.add(error);

      response.put("errors", errors);

      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse<String>> handleBadCredentials(BadCredentialsException ex) {
        ApiResponse<String> response = new ApiResponse<>("Invalid username or password", null);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public   ResponseEntity<ApiResponse<String>> handleNoSuchElement(NoSuchElementException ex) {
        ApiResponse<String> response = new ApiResponse<>(ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}

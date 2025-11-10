package com.Ecommerce.users.excception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}

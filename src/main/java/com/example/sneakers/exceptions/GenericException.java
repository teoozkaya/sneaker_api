package com.example.sneakers.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GenericException extends RuntimeException{
  private HttpStatus httpStatus;
  private String message;
  private int errorCode;
}

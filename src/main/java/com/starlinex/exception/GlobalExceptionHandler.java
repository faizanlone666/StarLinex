package com.starlinex.exception;

import com.starlinex.model.ServiceResponse;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.ServletException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException e){
        Map<String, String> errorMap = new HashMap<>();
        FieldError fieldError = e.getBindingResult().getFieldError();
        errorMap.put("message", fieldError.getDefaultMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(StarLinexException.class)
    public ServiceResponse handleUserAlreadyExistException(StarLinexException e){
        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setMessage(e.getMessage());
        serviceResponse.setResponseCode(500);
        return serviceResponse;
    }
    @ExceptionHandler(value = { ServletException.class })
    public ResponseEntity servletException(ServletException e) {
        String message = e.getMessage();
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        if (message.equals("token_expired")) {
            httpStatus = HttpStatus.UNAUTHORIZED;
            message = "the token is expired and not valid anymore";
        }
        //RestErrorResponse restErrorResponse = new RestErrorResponse(httpStatus, message);
        return ResponseEntity.status(httpStatus).body(message);
    }
}

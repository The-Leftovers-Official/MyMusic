package com.ciandt.summit.bootcamp2022.exceptions;


import com.ciandt.summit.bootcamp2022.exceptions.dto.ValidationErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthorizedHandler {


    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(IllegalArgumentException.class)
    private ValidationErrorDto handle(IllegalArgumentException exception){
        return ValidationErrorDto.builder().message(exception.getMessage()).build();
    }

}

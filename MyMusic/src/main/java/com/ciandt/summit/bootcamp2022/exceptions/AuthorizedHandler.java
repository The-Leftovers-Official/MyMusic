package com.ciandt.summit.bootcamp2022.exceptions;


import com.ciandt.summit.bootcamp2022.exceptions.dto.ValidationErrorDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class AuthorizedHandler extends ResponseEntityExceptionHandler {


    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(IllegalArgumentException.class)
    private ValidationErrorDto handle(IllegalArgumentException exception){
        return ValidationErrorDto.builder().message(exception.getMessage()).build();
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public static class InvalidRequestHeaderException extends RuntimeException {

        public InvalidRequestHeaderException() {
            super("Required request header 'Username' for method parameter type String is not present");
        }
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public static class TokenServiceException extends RuntimeException {

        public TokenServiceException() {
            super("Token Service not working! ");
        }
    }


    @ExceptionHandler(value = {
            MissingRequestHeaderException.class,
            InvalidRequestHeaderException.class
    })
    protected ResponseEntity<Object> handleRequestHeaderException(Exception ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorResponse.builder()
                .status(String.valueOf(HttpStatus.UNAUTHORIZED.value()))
                .reason(ex.getMessage()).build());
    }

    @ExceptionHandler(value = {
            TokenServiceException.class
    })
    protected ResponseEntity<Object> handleTokenServiceException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResponse.builder()
                .status(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .reason(ex.getMessage()).build());
    }


    @AllArgsConstructor
    @Getter
    @Builder
    public static class ErrorResponse {

        private String status;
        private String reason;
    }


}

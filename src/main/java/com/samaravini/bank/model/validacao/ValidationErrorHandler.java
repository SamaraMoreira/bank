package com.samaravini.bank.model.validacao;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class ValidationErrorHandler {

    record ValidationError(String campo, String mensagem) {
        public ValidationError(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public List<ValidationError> handler(MethodArgumentNotValidException exception){
        return exception
                .getFieldErrors()
                .stream()
                .map(ValidationError::new)
                .toList();
    }
}
package io.github.nibiroo.rest.controller;

import io.github.nibiroo.exception.BusinessRoleException;
import io.github.nibiroo.exception.PurchaseOrderNotFoundException;
import io.github.nibiroo.rest.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(BusinessRoleException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleBusinessRoleException(BusinessRoleException ex) {
        String errorMessage = ex.getMessage();
        return new ApiErrors(errorMessage);
    }

    @ExceptionHandler(PurchaseOrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handlePurchaseOrderNotFoundException(PurchaseOrderNotFoundException ex) {
        return new ApiErrors(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleMethodNotValidException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                                        .getAllErrors()
                                        .stream()
                                        .map(error -> error.getDefaultMessage())
                                        .collect(Collectors.toList());
        return new ApiErrors(errors);
    }
}

package com.project.retail_site.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.project.retail_site.exceptions.BillGenerationException;
import com.project.retail_site.exceptions.ProductNotFoundException;
import com.project.retail_site.exceptions.UserNotFoundException;

@RestControllerAdvice
public class GloblaExceptionHandler {
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(value= HttpStatus.NOT_FOUND)
    public ErrorResponse handleProductNotFoundException(ProductNotFoundException ex) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(value= HttpStatus.NOT_FOUND)
    public ErrorResponse handleUserNotFoundException(UserNotFoundException ex) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(BillGenerationException.class)
    @ResponseStatus(value= HttpStatus.NOT_FOUND)
    public ErrorResponse handleBillGenerationException(BillGenerationException ex) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(value= HttpStatus.NOT_FOUND)
    public ErrorResponse requestHandlingResourceFound(NoResourceFoundException ex) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Resource not found: "+ ex.getLocalizedMessage());
    }
}

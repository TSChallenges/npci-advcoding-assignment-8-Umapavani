package com.mystore.app.config;

import com.mystore.app.entity.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class MyGlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomException> handleValidationExceptions(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        StringBuilder builder = new StringBuilder();

        // Loop through each error and append it to the StringBuilder2
        if (bindingResult.hasErrors()) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                builder.append(fieldError.getDefaultMessage()).append(", ");
            }
        }

        // Remove the last comma and space
        if (builder.length() > 0) {
            builder.setLength(builder.length() - 2);  // Removing the last ", "
        }

        // Create the custom exception response
        CustomException customException = new CustomException(builder.toString(), HttpStatus.BAD_REQUEST.name());
        return new ResponseEntity<>(customException, HttpStatus.BAD_REQUEST);
    }
}

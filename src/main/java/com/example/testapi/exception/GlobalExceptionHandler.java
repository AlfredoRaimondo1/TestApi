package com.example.testapi.exception;

import com.example.testapi.model.dto.ExceptionMoneyTransferResponse;
import com.example.testapi.model.dto.ExceptionResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionResponse> handleConstraintViolationException(ConstraintViolationException ex) {
        ExceptionResponse error = new ExceptionResponse("Invalid request", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpClientErrorException.Forbidden.class)
    public ResponseEntity<ExceptionResponse> handleForbiddenException(HttpClientErrorException.Forbidden ex) {
        ExceptionResponse error = new ExceptionResponse("Invalid account identifier", HttpStatus.FORBIDDEN.value());
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    public ResponseEntity<ExceptionResponse> handleBadRequestException(HttpClientErrorException.BadRequest ex) {
        ExceptionResponse error = new ExceptionResponse("Invalid request", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ExceptionResponse error = new ExceptionResponse("Validation failed", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(HttpMessageConversionException.class)
    public ResponseEntity<ExceptionResponse> handleForbiddenException(HttpMessageConversionException ex) {
        ExceptionResponse error = new ExceptionResponse("Validation failed", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(InternalException.class)
    public ResponseEntity<ExceptionResponse> handleForbiddenException(InternalException ex) {
        ExceptionResponse error = new ExceptionResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(MoneyTransferException.class)
    public ResponseEntity<ArrayList<ExceptionMoneyTransferResponse>> handleMoneyTransferException(MoneyTransferException ex) {
        ArrayList<ExceptionMoneyTransferResponse> error = new ArrayList<ExceptionMoneyTransferResponse>(ex.getError()
                .stream()
                .map(item -> new ExceptionMoneyTransferResponse(item.getCode(), item.getDescription())).toList());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> handleForbiddenException(IllegalArgumentException ex) {
        ExceptionResponse error = new ExceptionResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}

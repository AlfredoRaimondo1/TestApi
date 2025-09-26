package com.example.testapi.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionResponse {
    private String message;
    private int status;

    public ExceptionResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }
}

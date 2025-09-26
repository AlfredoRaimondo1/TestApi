package com.example.testapi.exception;

import com.example.testapi.model.dto.ExtFabrickError;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class InternalException extends RuntimeException {

    public InternalException(String message) {
        super(message);
    }
}

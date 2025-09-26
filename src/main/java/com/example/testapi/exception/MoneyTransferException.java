package com.example.testapi.exception;

import com.example.testapi.model.dto.ExtFabrickError;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MoneyTransferException extends RuntimeException {
    private final List<ExtFabrickError> error;

    public MoneyTransferException(String message, List<ExtFabrickError> error) {
        super(message);
        this.error = error;
    }
}

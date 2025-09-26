package com.example.testapi.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionTypeDto {
    private String enumeration;
    private String value;

    public TransactionTypeDto(String enumeration, String value) {
        this.enumeration = enumeration;
        this.value = value;
    }
}

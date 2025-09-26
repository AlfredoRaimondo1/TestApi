package com.example.testapi.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExceptionMoneyTransferResponse {
    String code;
    String description;

    public ExceptionMoneyTransferResponse(String code, String description) {
        this.code = code;
        this.description = description;
    }
}

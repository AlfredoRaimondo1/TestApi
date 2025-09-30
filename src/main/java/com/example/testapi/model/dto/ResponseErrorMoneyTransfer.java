package com.example.testapi.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseErrorMoneyTransfer {
    private String code;
    private String description;
    private String params;

    public ResponseErrorMoneyTransfer(String code, String description, String params) {
        this.code = code;
        this.description = description;
        this.params = params;
    }
}

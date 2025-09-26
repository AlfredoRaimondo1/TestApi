package com.example.testapi.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FeeDto {
    private String feeCode;
    private String description;
    private Number amount;
    private String currency;

    public FeeDto(String feeCode, String description, Number amount, String currency) {
        this.feeCode = feeCode;
        this.description = description;
        this.amount = amount;
        this.currency = currency;
    }
}

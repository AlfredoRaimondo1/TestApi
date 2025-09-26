package com.example.testapi.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class TransactionDto {
    private String transactionId;
    private String operationId;
    private Date accountingDate;
    private Date valueDate;
    private TransactionTypeDto type;
    private Number amount;
    private String currency;
    private String description;

    public TransactionDto(String transactionId, String operationId, Date accountingDate, Date valueDate, TransactionTypeDto type, Number amount, String currency, String description) {
        this.transactionId = transactionId;
        this.operationId = operationId;
        this.accountingDate = accountingDate;
        this.valueDate = valueDate;
        this.type = type;
        this.amount = amount;
        this.currency = currency;
        this.description = description;
    }
}

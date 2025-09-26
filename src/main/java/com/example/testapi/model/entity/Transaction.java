package com.example.testapi.model.entity;

import com.example.testapi.model.dto.TransactionDto;
import com.example.testapi.model.dto.TransactionTypeDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "transaction")
@Getter
@Setter
public class Transaction {

    @Id
    private String transactionId;
    private String operationId;
    private Date accountingDate;
    private Date valueDate;
    private String typeEnumeration;
    private String typeValue;
    private Double amount;
    private String currency;
    private String description;

    public Transaction(String transactionId, String operationId, Date accountingDate, Date valueDate, String typeEnumeration, String typeValue, Number amount, String currency, String description) {
        this.transactionId = transactionId;
        this.operationId = operationId;
        this.accountingDate = accountingDate;
        this.valueDate = valueDate;
        this.typeEnumeration = typeEnumeration;
        this.typeValue = typeValue;
        this.amount = amount.doubleValue();
        this.currency = currency;
        this.description = description;
    }

    public Transaction(TransactionDto tr) {
        this.transactionId = tr.getTransactionId();
        this.operationId = tr.getOperationId();
        this.accountingDate = tr.getAccountingDate();
        this.valueDate = tr.getValueDate();
        this.typeEnumeration = tr.getType().getEnumeration();
        this.typeValue = tr.getType().getValue();
        this.amount = tr.getAmount().doubleValue();
        this.currency = tr.getCurrency();
        this.description = tr.getDescription();
    }

    public Transaction() {

    }
}

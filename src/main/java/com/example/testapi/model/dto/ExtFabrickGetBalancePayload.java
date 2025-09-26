package com.example.testapi.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ExtFabrickGetBalancePayload {
    private Date date;
    private Number balance;
    private Number availableBalance;
    private String currency;

    public ExtFabrickGetBalancePayload(Date date, Number balance, Number availableBalance, String currency) {
        this.date = date;
        this.balance = balance;
        this.availableBalance = availableBalance;
        this.currency = currency;
    }
}

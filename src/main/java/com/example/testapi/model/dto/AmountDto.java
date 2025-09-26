package com.example.testapi.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class AmountDto {
    private Number debtorAmount;
    private String debtorCurrency;
    private Number creditorAmount;
    private String creditorCurrency;
    private Date creditorCurrencyDate;
    private String exchangeDate;

    public AmountDto(Number debtorAmount, String debtorCurrency, Number creditorAmount, String creditorCurrency, Date creditorCurrencyDate, String exchangeDate) {
        this.debtorAmount = debtorAmount;
        this.debtorCurrency = debtorCurrency;
        this.creditorAmount = creditorAmount;
        this.creditorCurrency = creditorCurrency;
        this.creditorCurrencyDate = creditorCurrencyDate;
        this.exchangeDate = exchangeDate;
    }
}

package com.example.testapi.service;

import com.example.testapi.model.dto.ExtFabrickGetTransactionListPayload;
import com.example.testapi.model.dto.ResponseGetBalanceDto;

import java.time.LocalDate;

public interface IAccountService {

    public ResponseGetBalanceDto getBalance(long accountId);
    public ExtFabrickGetTransactionListPayload getTransactionList(Long accountId, LocalDate fromAccountingDate, LocalDate toAccountingDate);


}

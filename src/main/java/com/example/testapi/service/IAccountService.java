package com.example.testapi.service;

import com.example.testapi.model.dto.ResponseGetBalanceDto;
import com.example.testapi.model.dto.ResponseGetTransactionList;

import java.time.LocalDate;

public interface IAccountService {

    public ResponseGetBalanceDto getBalance(long accountId);
    public ResponseGetTransactionList getTransactionList(Long accountId, LocalDate fromAccountingDate, LocalDate toAccountingDate);


}

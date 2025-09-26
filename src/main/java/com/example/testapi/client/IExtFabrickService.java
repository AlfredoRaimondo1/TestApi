package com.example.testapi.client;

import com.example.testapi.model.dto.*;

import java.time.LocalDate;

public interface IExtFabrickService {

    public ExtFabrickApiResponse<ExtFabrickGetBalancePayload> extGetBalance(long accountId);
    public ExtFabrickApiResponse<ExtFabrickGetTransactionListPayload> extGetTransactionList(long accountId, LocalDate fromAccountingDate, LocalDate toAccountingDate);
    public ExtFabrickApiResponse<ExtFabrickMoneyTransferPayload> extMoneyTransfer(long accountId, RequestMoneyTransfersDto requestBody);

}

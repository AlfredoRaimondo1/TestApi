package com.example.testapi.service;

import com.example.testapi.model.dto.RequestMoneyTransfersDto;
import com.example.testapi.model.dto.ResponseMoneyTransferDto;

public interface IOperationService {
    public ResponseMoneyTransferDto moneyTransfer(long accountId, RequestMoneyTransfersDto requestBody);

}

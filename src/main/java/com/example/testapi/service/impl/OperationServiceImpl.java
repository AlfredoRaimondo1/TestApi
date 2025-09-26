package com.example.testapi.service.impl;

import com.example.testapi.client.IExtFabrickService;
import com.example.testapi.model.dto.ExtFabrickApiResponse;
import com.example.testapi.model.dto.ExtFabrickMoneyTransferPayload;
import com.example.testapi.model.dto.RequestMoneyTransfersDto;
import com.example.testapi.model.dto.ResponseMoneyTransferDto;
import com.example.testapi.service.IOperationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OperationServiceImpl implements IOperationService {

    private final IExtFabrickService extFabrickService;


    @Override
    public ResponseMoneyTransferDto moneyTransfer(long accountId, RequestMoneyTransfersDto requestBody) {

        ExtFabrickApiResponse<ExtFabrickMoneyTransferPayload> extResponse = extFabrickService.extMoneyTransfer(accountId, requestBody);


        return new ResponseMoneyTransferDto(extResponse.getPayload(), extResponse.getErrors());

    }
}

package com.example.testapi.service.impl;

import com.example.testapi.client.IExtFabrickService;
import com.example.testapi.exception.InternalException;
import com.example.testapi.model.dto.*;
import com.example.testapi.model.entity.Transaction;
import com.example.testapi.repository.TransactionRepository;
import com.example.testapi.service.IAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements IAccountService {
    private final IExtFabrickService extFabrickService;
    private final TransactionRepository transactionRepository;

    @Override
    public ResponseGetBalanceDto getBalance(long accountId) {
        ExtFabrickApiResponse<ExtFabrickGetBalancePayload> extResponse = extFabrickService.extGetBalance(accountId);

        if(extResponse.getPayload() == null){
            log.error("Error API: received payload from getBalance is null");
            throw new InternalException("Error API: received payload from getBalance is null");
        }

        return new ResponseGetBalanceDto(accountId,
                extResponse.getPayload().getBalance()+extResponse.getPayload().getCurrency());
    }

    @Override
    public ResponseGetTransactionList getTransactionList(Long accountId, LocalDate fromAccountingDate, LocalDate toAccountingDate) {

        ExtFabrickApiResponse<ExtFabrickGetTransactionListPayload> extResponse = extFabrickService.extGetTransactionList(accountId, fromAccountingDate, toAccountingDate);

        if(extResponse.getPayload() == null){
            log.error("Error API: received payload from getTransactionList is null");
            throw new InternalException("Error API: received payload from getTransactionList is null");
        }

        ResponseGetTransactionList responseGetTransactionList = new ResponseGetTransactionList(extResponse.getPayload());

        List<Transaction> transactionToSave = new ArrayList<>(List.of());
        List<TransactionDto> tr = responseGetTransactionList.getList();
        tr.forEach(t -> {
            Transaction transaction = new Transaction(t);
            transactionToSave.add(transaction);
        });
        transactionRepository.saveAll(transactionToSave);
        log.info("Update data into database");
        return responseGetTransactionList;

    }


}

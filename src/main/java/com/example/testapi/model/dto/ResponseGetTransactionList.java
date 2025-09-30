package com.example.testapi.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ResponseGetTransactionList {
    private List<TransactionDto> list;

    public ResponseGetTransactionList(List<TransactionDto> list) {
        this.list = list;
    }

    public ResponseGetTransactionList(ExtFabrickGetTransactionListPayload extFabrickGetTransactionList) {
        this.list = extFabrickGetTransactionList.getList();
    }
}

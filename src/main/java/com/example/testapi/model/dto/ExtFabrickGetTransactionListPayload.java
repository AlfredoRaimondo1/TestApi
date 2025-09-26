package com.example.testapi.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ExtFabrickGetTransactionListPayload {
    private List<TransactionDto> list;

    public ExtFabrickGetTransactionListPayload(List<TransactionDto> list) {
        this.list = list;
    }
}

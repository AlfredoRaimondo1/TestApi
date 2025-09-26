package com.example.testapi.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseGetBalanceDto {

    private Long accountId;
    private String balance;

    public ResponseGetBalanceDto(Long accountId, String balance) {
        this.accountId = accountId;
        this.balance = balance;
    }
}

package com.example.testapi.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DebtorDto {
    private String name;

    private AccountDto account;

    public DebtorDto(String name, AccountDto account) {
        this.name = name;
        this.account = account;
    }
}

package com.example.testapi.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
@Getter
@Setter
@NoArgsConstructor
public class AccountDto {
    @NotNull
    private String accountCode;

    private String bicCode;

    public AccountDto(@NotNull String accountCode, String bicCode) {
        this.accountCode = accountCode;
        this.bicCode = bicCode;
    }
}

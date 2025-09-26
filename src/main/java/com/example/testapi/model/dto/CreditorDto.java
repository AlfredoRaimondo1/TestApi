package com.example.testapi.model.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class CreditorDto {
    @NotNull
    @Size(max = 50)
    private String name;

    @NotNull
    private AccountDto account;

    private AddressDto address;

    public CreditorDto(@NotNull String name, @NotNull AccountDto account, AddressDto address) {
        this.name = name;
        this.account = account;
        this.address = address;
    }
}

package com.example.testapi.model.dto;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ResponseMoneyTransferDto {
    private ExtFabrickMoneyTransferPayload payload;
    private List<ExtFabrickError> error;

    public ResponseMoneyTransferDto(ExtFabrickMoneyTransferPayload payload, List<ExtFabrickError> error) {
        this.payload = payload;
        this.error = error;
    }
}

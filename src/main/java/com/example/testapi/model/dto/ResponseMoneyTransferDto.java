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
    private ResponseFabrickMoneyTransfer payload;
    private List<ExtFabrickError> error;

    public ResponseMoneyTransferDto(ResponseFabrickMoneyTransfer payload, List<ExtFabrickError> error) {
        this.payload = payload;
        this.error = error;
    }

    public ResponseMoneyTransferDto(ExtFabrickMoneyTransferPayload payload, List<ExtFabrickError> error) {
        this.payload = new ResponseFabrickMoneyTransfer(payload);
        this.error = error;
    }
}

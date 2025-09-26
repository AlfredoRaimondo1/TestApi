package com.example.testapi.controller;

import com.example.testapi.model.dto.RequestMoneyTransfersDto;
import com.example.testapi.model.dto.ResponseMoneyTransferDto;
import com.example.testapi.service.IOperationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/operation/")
public class OperationController {

    private final IOperationService operationService;

    /**
     * richiesta per operazione di bonifico
     * @param accountId  account identifier di chi vuole effettuare l'operazione di bonifico
     * @param requestBody dati necessari per eseguire l'operazione di bonifico
     * @return restituisce risposta sullo stato dell'esecuzione del bonifico
     */
    @PostMapping("/{accountId}/money-transfer")
    public ResponseEntity<?> moneyTransfer(@PathVariable @NotNull @Positive Long accountId,
                                            @RequestBody @Valid RequestMoneyTransfersDto requestBody) {
        log.info("Received request to execute money transfer for accountId: {}", accountId);

        ResponseMoneyTransferDto response = operationService.moneyTransfer(accountId, requestBody);

        return ResponseEntity.ok(response.getPayload());
    }
}

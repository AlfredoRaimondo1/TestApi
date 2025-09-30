package com.example.testapi.controller;

import com.example.testapi.model.dto.ResponseGetBalanceDto;
import com.example.testapi.model.dto.ResponseGetTransactionList;
import com.example.testapi.service.IAccountService;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@Slf4j
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/account/")
public class AccountController {

    private final IAccountService accountService;



    /**
     * recupera il saldo di un account
     * @param accountId account identifier di cui si vuole recuperare il saldo
     * @return restituisce il saldo associato all'account identifier richiesto
     */
    @GetMapping("/{accountId}/get-balance")
    public ResponseEntity<ResponseGetBalanceDto> getBalance(@PathVariable @NotNull @Positive Long accountId) {
        log.info("Received request to get balance for accountId: {}", accountId);

        return ResponseEntity.ok(accountService.getBalance(accountId));
    }


    /**
     * Recupera la lista transazioni di un account
     * @param accountId  account identifier di cui si vuole recuperare il saldo
     * @param fromAccountingDate data di partenza da cui recuperare le transazioni
     * @param toAccountingDate data di fine di cui recuperare le transazioni
     * @return restituisce la lista di transazioni associate all'account identifier richiesto
     */
    @GetMapping("/{accountId}/get-transaction-list")
    public ResponseEntity<ResponseGetTransactionList> getTransactionList(@PathVariable Long accountId, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromAccountingDate, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toAccountingDate) {
        log.info("Received request to transaction list for accountId: {}", accountId);

        ResponseGetTransactionList response = accountService.getTransactionList(accountId, fromAccountingDate, toAccountingDate);

        return ResponseEntity.ok(response);

    }


}

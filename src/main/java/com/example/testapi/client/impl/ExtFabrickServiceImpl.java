package com.example.testapi.client.impl;

import com.example.testapi.client.IExtFabrickService;
import com.example.testapi.exception.InternalException;
import com.example.testapi.exception.MoneyTransferException;
import com.example.testapi.model.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExtFabrickServiceImpl implements IExtFabrickService {

    private final RestClient restExtClient;
    private final RestClient restClient;

    @Value("${external.client.getbalance}")
    private String getBalanceUrl;

    @Value("${external.client.gettransaction}")
    private String getTransactionUrl;

    @Value("${external.client.moneytransfers}")
    private String getMoneyTransfersUrl;

    /**
     * richiama servizio esterno fabrick per recuperare il saldo di un account
     * @param accountId account identifier di cui si vuole recuperare il saldo
     * @return restituisce risposta esterna di fabrick
     */
    @Override
    public ExtFabrickApiResponse<ExtFabrickGetBalancePayload> extGetBalance(long accountId) {
        log.info("Call external api to get balance for accountId: {}", accountId);

        return restExtClient.get()
                .uri(getBalanceUrl, accountId)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<ExtFabrickApiResponse<ExtFabrickGetBalancePayload>>() {})
                .getBody();
    }

    /**
     * richiama servizio esterno fabrick per recuperare lista transazioni di un account
     * @param accountId  account identifier di cui si vuole recuperare la lista di transazioni
     * @param fromAccountingDate data di partenza da cui recuperare le transazioni
     * @param toAccountingDate data di fine di cui recuperare le transazioni
     * @return  restituisce risposta esterna di fabrick
     */
    @Override
    public ExtFabrickApiResponse<ExtFabrickGetTransactionListPayload> extGetTransactionList(long accountId, LocalDate fromAccountingDate, LocalDate toAccountingDate) {
        log.info("Call external api to get transaction list for accountId: {}", accountId);

        return restExtClient.get()
                .uri(getTransactionUrl, accountId, fromAccountingDate, toAccountingDate)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<ExtFabrickApiResponse<ExtFabrickGetTransactionListPayload>>() {})
                .getBody();
    }

    /**
     * richiama servizio esterno fabrick per operazione di bonifico
     * @param accountId  account identifier di chi vuole effettuare l'operazione di bonifico
     * @param requestBody dati necessari per eseguire l'operazione di bonifico
     * @return restituisce risposta esterna di fabrick
     */
    @Override
    public ExtFabrickApiResponse<ExtFabrickMoneyTransferPayload> extMoneyTransfer(long accountId, RequestMoneyTransfersDto requestBody) {
        log.info("Call external api to money transfer for accountId: {}", accountId);

//        return restExtClient.post()
//                .uri("api/gbs/banking/v4.0/accounts/{accountId}/payments/money-transfers", accountId)
//                .body(requestBody)
//                .retrieve()
//                .toEntity(new ParameterizedTypeReference<ExtFabrickApiResponse<ExtFabrickMoneyTransferPayload>>() {})
//                .getBody();



        ResponseEntity<ExtFabrickApiResponse<ExtFabrickMoneyTransferPayload>> responseEntity =  restExtClient.post()
                .uri(getMoneyTransfersUrl, accountId)
                .body(requestBody)
                .exchange((clientRequest, clientResponse) -> {

                            HttpStatusCode statusCode = clientResponse.getStatusCode();
                            log.info("Response status: {}", statusCode);

                            if (statusCode.value() == HttpStatus.BAD_REQUEST.value()) {
                                log.error("Bad Request - Invalid money transfer data");

                                ExtFabrickApiResponse<ExtFabrickMoneyTransferPayload> errorBody = clientResponse.bodyTo(new ParameterizedTypeReference<ExtFabrickApiResponse<ExtFabrickMoneyTransferPayload>>() {});
                                log.error("Error body: {}", errorBody);

                                assert errorBody != null;
                                throw new MoneyTransferException("Dati per il money transfer non validi", errorBody.getErrors());
                            }

                    throw new InternalException("Status code non gestito: " + statusCode.value());

                });

        assert responseEntity != null;
        return responseEntity.getBody();
    }




}

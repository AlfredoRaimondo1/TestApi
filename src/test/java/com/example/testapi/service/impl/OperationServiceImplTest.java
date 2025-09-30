package com.example.testapi.service.impl;

import com.example.testapi.client.IExtFabrickService;
import com.example.testapi.exception.MoneyTransferException;
import com.example.testapi.model.dto.ExtFabrickApiResponse;
import com.example.testapi.model.dto.ExtFabrickMoneyTransferPayload;
import com.example.testapi.model.dto.RequestMoneyTransfersDto;
import com.example.testapi.repository.TransactionRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OperationServiceImplTest {
    @Mock
    private IExtFabrickService extFabrickService;

    @InjectMocks
    private OperationServiceImpl operationService;

    @Mock
    private TransactionRepository transactionRepository;

    @BeforeEach
    void setUp() {
        Mockito.reset(extFabrickService, transactionRepository);
    }

    @Test
    void givenValidAccountId_whenSendMoneyTransfer_thenReturnBadRequest() throws IOException {
        //given
        long accountId = 123;

        ObjectMapper objectMapper = new ObjectMapper();
        RequestMoneyTransfersDto requestMoneyTransfersDto = objectMapper.readValue(
                new File("src/test/resources/ValidRequestMoneyTransfersDtoTest.json"),
                RequestMoneyTransfersDto.class
        );
        ExtFabrickApiResponse<ExtFabrickMoneyTransferPayload> extResponseMoneyTransfer = objectMapper.readValue(
                new File("src/test/resources/BadRequestResponseMoneyTransfer.json"),
                new TypeReference<ExtFabrickApiResponse<ExtFabrickMoneyTransferPayload>>() {}
        );

        ResponseEntity<ExtFabrickApiResponse<ExtFabrickMoneyTransferPayload>> extResponse =
                new ResponseEntity<ExtFabrickApiResponse<ExtFabrickMoneyTransferPayload>>(extResponseMoneyTransfer, HttpStatus.BAD_REQUEST);

        //when
        when(extFabrickService.extMoneyTransfer(accountId, requestMoneyTransfersDto)).thenThrow(MoneyTransferException.class);
        //ResponseMoneyTransferDto result = operationService.moneyTransfer(accountId, requestMoneyTransfersDto);

        //then
        assertThrows(MoneyTransferException.class, () ->
                operationService.moneyTransfer(accountId, requestMoneyTransfersDto));

    }

    @Test
    void givenValidAccountId_whenSendMoneyTransfer_thenReturnValidationFailed() throws IOException {
        //given
        long accountId = 123;

        ObjectMapper objectMapper = new ObjectMapper();
        RequestMoneyTransfersDto requestMoneyTransfersDto = objectMapper.readValue(
                new File("src/test/resources/InvalidRequestMoneyTransfersDtoTest.json"),
                RequestMoneyTransfersDto.class
        );
        ExtFabrickApiResponse<ExtFabrickMoneyTransferPayload> extResponseMoneyTransfer = objectMapper.readValue(
                new File("src/test/resources/BadRequestResponseMoneyTransfer.json"),
                new TypeReference<ExtFabrickApiResponse<ExtFabrickMoneyTransferPayload>>() {}
        );

        ResponseEntity<ExtFabrickApiResponse<ExtFabrickMoneyTransferPayload>> extResponse =
                new ResponseEntity<ExtFabrickApiResponse<ExtFabrickMoneyTransferPayload>>(extResponseMoneyTransfer, HttpStatus.BAD_REQUEST);

        //when
        when(extFabrickService.extMoneyTransfer(accountId, requestMoneyTransfersDto)).thenThrow(MoneyTransferException.class);

        //then
        MoneyTransferException ex = assertThrows(MoneyTransferException.class, () ->
                operationService.moneyTransfer(accountId, requestMoneyTransfersDto));
    }
}
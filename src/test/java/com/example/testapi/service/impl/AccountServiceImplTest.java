package com.example.testapi.service.impl;

import com.example.testapi.client.IExtFabrickService;
import com.example.testapi.model.dto.*;
import com.example.testapi.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {
    @Mock
    private IExtFabrickService extFabrickService;

    @InjectMocks
    private AccountServiceImpl accountService;

    @Mock
    private TransactionRepository transactionRepository;


    @BeforeEach
    void setUp() {
        Mockito.reset(extFabrickService, transactionRepository);
    }

    @Test
    void givenValidAccountId_whenGetBalance_thenReturnBalance(){
        //given
        long accountId = 123;
        ExtFabrickApiResponse<ExtFabrickGetBalancePayload> extResponseGetBalance = new ExtFabrickApiResponse<ExtFabrickGetBalancePayload>("OK", new ArrayList<ExtFabrickError>(),
                new ExtFabrickGetBalancePayload());

        ExtFabrickGetBalancePayload payload = new ExtFabrickGetBalancePayload();
        payload.setDate(new Date());
        payload.setBalance(8688.61);
        payload.setAvailableBalance(8688.61);
        payload.setCurrency("EUR");

        extResponseGetBalance.setPayload(payload);

        ResponseEntity<ExtFabrickApiResponse<ExtFabrickGetBalancePayload>> extResponse =
                new ResponseEntity<ExtFabrickApiResponse<ExtFabrickGetBalancePayload>>(extResponseGetBalance, HttpStatus.OK);

        //when
        when(extFabrickService.extGetBalance(accountId)).thenReturn(extResponse.getBody());

        ResponseGetBalanceDto result = accountService.getBalance(accountId);

        //then
        verify(extFabrickService).extGetBalance(accountId);
        assertThat(result.getAccountId()).isEqualTo(accountId);
        assertThat(result.getBalance()).isEqualTo("8688.61EUR");
    }



    @Test
    void givenInvalidAccountId_whenGetBalance_thenReturnForbidden(){
        //given
        long accountId = 10;
        ExtFabrickApiResponse<ExtFabrickGetBalancePayload> extResponseGetBalance = new ExtFabrickApiResponse<ExtFabrickGetBalancePayload>("KO", new ArrayList<ExtFabrickError>(),
                new ExtFabrickGetBalancePayload());

        ExtFabrickError extFabrickError = new ExtFabrickError("ERR001", "Invalid Account Identifier", null);
        List<ExtFabrickError> errors = List.of(extFabrickError);
        extResponseGetBalance.setErrors(errors);

        ResponseEntity<ExtFabrickApiResponse<ExtFabrickGetBalancePayload>> extResponse =
                new ResponseEntity<ExtFabrickApiResponse<ExtFabrickGetBalancePayload>>(extResponseGetBalance, HttpStatus.FORBIDDEN);

        //when
        when(extFabrickService.extGetBalance(accountId)).thenThrow(HttpClientErrorException.Forbidden.class);

        //then
        assertThrows(HttpClientErrorException.Forbidden.class, () ->
                accountService.getBalance(accountId));

    }


    @Test
    void givenValidAccountId_whenGetTransactionList_thenReturnTransactionList(){
        //given
        long accountId = 123;
        LocalDate fromAccountingDate = LocalDate.parse("2019-01-01");
        LocalDate toAccountingDate = LocalDate.parse("2019-12-01");

        ExtFabrickApiResponse<ExtFabrickGetTransactionListPayload> extResponseGetTransactionList = new ExtFabrickApiResponse<ExtFabrickGetTransactionListPayload>("OK", new ArrayList<ExtFabrickError>(),
                new ExtFabrickGetTransactionListPayload());
        LocalDate localDate = LocalDate.parse("2019-11-29");
        TransactionDto t1 = new TransactionDto("1", "00000000282831",
                Date.from(OffsetDateTime.parse("2019-11-29T00:00:00.000+00:00").toInstant()),
                Date.from(OffsetDateTime.parse("2019-12-01T00:00:00.000+00:00").toInstant()),
                 new TransactionTypeDto( "GBS_TRANSACTION_TYPE","GBS_ACCOUNT_TRANSACTION_TYPE_0050"),
                                            -343.77, "EUR","PD VISA CORPORATE 10");

        TransactionDto t2 =  new TransactionDto("2", "00000000282831",
                Date.from(OffsetDateTime.parse("2019-11-29T00:00:00.000+00:00").toInstant()),
                Date.from(OffsetDateTime.parse("2019-12-01T00:00:00.000+00:00").toInstant()), new TransactionTypeDto( "GBS_TRANSACTION_TYPE","GBS_ACCOUNT_TRANSACTION_TYPE_0050"),
                143.77, "EUR","PD VISA CORPORATE 10");


        List<TransactionDto> list = List.of(t1,t2);
        extResponseGetTransactionList.getPayload().setList(list);


        ResponseEntity<ExtFabrickApiResponse<ExtFabrickGetTransactionListPayload>> extResponse =
                new ResponseEntity<ExtFabrickApiResponse<ExtFabrickGetTransactionListPayload>>(extResponseGetTransactionList, HttpStatus.OK);

        //when
        when(extFabrickService.extGetTransactionList(accountId, fromAccountingDate, toAccountingDate)).thenReturn(extResponse.getBody());

        ResponseGetTransactionList result = accountService.getTransactionList(accountId, fromAccountingDate, toAccountingDate);

        //then
        verify(extFabrickService).extGetTransactionList(accountId, fromAccountingDate, toAccountingDate);
        assertFalse(result.getList().isEmpty());
        assertEquals(t1.getTransactionId(), result.getList().get(0).getTransactionId());
        assertEquals(t2.getTransactionId(), result.getList().get(1).getTransactionId());
    }

    @Test
    void givenValidAccountId_whenGetTransactionList_thenReturnEmptyTransactionList(){
        //given
        long accountId = 123;
        LocalDate fromAccountingDate = LocalDate.parse("2019-01-01");
        LocalDate toAccountingDate = LocalDate.parse("2019-12-01");

        ExtFabrickApiResponse<ExtFabrickGetTransactionListPayload> extResponseGetTransactionList = new ExtFabrickApiResponse<ExtFabrickGetTransactionListPayload>("OK", new ArrayList<ExtFabrickError>(),
                new ExtFabrickGetTransactionListPayload());
        LocalDate localDate = LocalDate.parse("2019-11-29");

        List<TransactionDto> list = List.of();
        extResponseGetTransactionList.getPayload().setList(list);


        ResponseEntity<ExtFabrickApiResponse<ExtFabrickGetTransactionListPayload>> extResponse =
                new ResponseEntity<ExtFabrickApiResponse<ExtFabrickGetTransactionListPayload>>(extResponseGetTransactionList, HttpStatus.OK);

        //when
        when(extFabrickService.extGetTransactionList(accountId, fromAccountingDate, toAccountingDate)).thenReturn(extResponse.getBody());

        ResponseGetTransactionList result = accountService.getTransactionList(accountId, fromAccountingDate, toAccountingDate);

        //then
        verify(extFabrickService).extGetTransactionList(accountId, fromAccountingDate, toAccountingDate);
        assertTrue(result.getList().isEmpty());
    }


    @Test
    void givenInvalidAccountId_whenGetTransactionList_thenReturnTransactionList(){
        //given
        long accountId = 123;
        LocalDate fromAccountingDate = LocalDate.parse("2019-01-01");
        LocalDate toAccountingDate = LocalDate.parse("2019-12-01");

        ExtFabrickApiResponse<ExtFabrickGetTransactionListPayload> extResponseGetTransactionList = new ExtFabrickApiResponse<ExtFabrickGetTransactionListPayload>("OK", new ArrayList<ExtFabrickError>(),
                new ExtFabrickGetTransactionListPayload());
        LocalDate localDate = LocalDate.parse("2019-11-29");
        TransactionDto t1 = new TransactionDto("1", "00000000282831",
                Date.from(OffsetDateTime.parse("2019-11-29T00:00:00.000+00:00").toInstant()),
                Date.from(OffsetDateTime.parse("2019-12-01T00:00:00.000+00:00").toInstant()),
                new TransactionTypeDto( "GBS_TRANSACTION_TYPE","GBS_ACCOUNT_TRANSACTION_TYPE_0050"),
                -343.77, "EUR","PD VISA CORPORATE 10");

        TransactionDto t2 =  new TransactionDto("2", "00000000282831",
                Date.from(OffsetDateTime.parse("2019-11-29T00:00:00.000+00:00").toInstant()),
                Date.from(OffsetDateTime.parse("2019-12-01T00:00:00.000+00:00").toInstant()), new TransactionTypeDto( "GBS_TRANSACTION_TYPE","GBS_ACCOUNT_TRANSACTION_TYPE_0050"),
                143.77, "EUR","PD VISA CORPORATE 10");


        List<TransactionDto> list = List.of(t1,t2);
        extResponseGetTransactionList.getPayload().setList(list);


        ResponseEntity<ExtFabrickApiResponse<ExtFabrickGetTransactionListPayload>> extResponse =
                new ResponseEntity<ExtFabrickApiResponse<ExtFabrickGetTransactionListPayload>>(extResponseGetTransactionList, HttpStatus.OK);

        //when
        when(extFabrickService.extGetTransactionList(accountId, fromAccountingDate, toAccountingDate)).thenThrow(HttpClientErrorException.Forbidden.class);

        //then
        assertThrows(HttpClientErrorException.Forbidden.class, () ->
                accountService.getTransactionList(accountId, fromAccountingDate, toAccountingDate));
    }
}
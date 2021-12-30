package com.sbrf.reboot.service;

import com.sbrf.reboot.repository.AccountRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AccountServiceTest {

    @Mock
    AccountRepository accountRepository;

    AccountService accountService;

    @BeforeEach
    void setUp() {
        accountRepository = Mockito.mock(AccountRepository.class);

        accountService = new AccountService(accountRepository);
    }

    @SneakyThrows
    @Test
    void contractExist() {
        Set<Long> accounts = new HashSet();
        accounts.add(111L);

        long clientId = 1L;
        long contractNumber = 111L;


        when(accountRepository.getAllAccountsByClientId(clientId)).thenReturn(accounts);

        assertTrue(accountService.isClientHasContract(clientId, contractNumber));
    }

    @SneakyThrows
    @Test
    void contractNotExist() {
        Set<Long> accounts = new HashSet();
        accounts.add(222L);

        long clientId = 1L;
        long contractNumber = 111L;

        when(accountRepository.getAllAccountsByClientId(clientId)).thenReturn(accounts);

        assertFalse(accountService.isClientHasContract(clientId, contractNumber));
    }

    @SneakyThrows
    @Test
    void creditDebtExist() {
        Map<Long, BigDecimal> accountsAndBalance = new HashMap<>();
        accountsAndBalance.put(111L, BigDecimal.valueOf(-1000343.43));
        accountsAndBalance.put(222L, BigDecimal.valueOf(2305.54));

        long clientId = 1L;

        when(accountRepository.getAllAccountsByClientId(clientId)).thenReturn(accountsAndBalance.keySet());

        for (long contractNumber : accountsAndBalance.keySet()) {
            when(accountRepository.getAccountBalanceByContractNumber(contractNumber))
                    .thenReturn(accountsAndBalance.get(contractNumber));
        }

        assertTrue(accountService.isClientHasCreditDebt(clientId));
    }

    @SneakyThrows
    @Test
    void creditDebtNotExist() {
        Map<Long, BigDecimal> accountsAndBalance = new HashMap<>();
        accountsAndBalance.put(111L, BigDecimal.valueOf(4356.43));
        accountsAndBalance.put(222L, BigDecimal.valueOf(2305.54));

        long clientId = 1L;

        when(accountRepository.getAllAccountsByClientId(clientId)).thenReturn(accountsAndBalance.keySet());

        for (long contractNumber : accountsAndBalance.keySet()) {
            when(accountRepository.getAccountBalanceByContractNumber(contractNumber))
                    .thenReturn(accountsAndBalance.get(contractNumber));
        }

        assertFalse(accountService.isClientHasCreditDebt(clientId));
    }

    @Test
    void repositoryHasTreeMethods() {
        assertEquals(2, AccountRepository.class.getMethods().length);
    }

    @Test
    void serviceHasTreeMethods() {
        assertEquals(2, AccountService.class.getMethods().length - Object.class.getMethods().length);
    }

}
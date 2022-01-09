package com.sbrf.reboot.service;

import com.sbrf.reboot.repository.AccountRepository;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@RequiredArgsConstructor
@FieldDefaults(makeFinal=true, level= AccessLevel.PRIVATE)
public class AccountService {

    @NonNull AccountRepository accountRepository;

    public boolean isClientHasContract(long clientId, long contractNumber) {
        return accountRepository.getAllAccountsByClientId(clientId).contains(contractNumber);
    }

    public boolean isClientHasCreditDebt(long clientId){
        for (long contractNumber: accountRepository.getAllAccountsByClientId(clientId)) {
            if (accountRepository.getAccountBalanceByContractNumber(contractNumber).compareTo(BigDecimal.ZERO) < 0) {
                return true;
            }
        }
        return false;
    }
}

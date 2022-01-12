package com.sbrf.reboot.repository;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Set;

public interface AccountRepository {
    Set<Long> getAllAccountsByClientId(long clientId);

    void updateContractNumberByClientId(long clientId, long oldContractNumber, long newContractNumber);

    BigDecimal getAccountBalanceByContractNumber(long contractNumber);
}

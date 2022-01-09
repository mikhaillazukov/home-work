package com.sbrf.reboot.repository;

import java.math.BigDecimal;
import java.util.Set;

public interface AccountRepository {
    Set<Long> getAllAccountsByClientId(long clientId);

    BigDecimal getAccountBalanceByContractNumber(long contractNumber);
}

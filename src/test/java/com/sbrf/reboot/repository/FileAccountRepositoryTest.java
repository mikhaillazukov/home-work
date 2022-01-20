package com.sbrf.reboot.repository;

import com.sbrf.reboot.repository.impl.FileAccountRepository;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileAccountRepositoryTest {

    AccountRepository accountRepository;

    @Test
    void onlyPersonalAccounts() throws IOException {
        String filePath = "src/main/resources/Accounts.txt";
        accountRepository = new FileAccountRepository(filePath);

        long clientId = 1L;
        Set<Long> actualAccounts = accountRepository.getAllAccountsByClientId(clientId);

        Set<Long> expected = new HashSet<Long>() {{
            add(111L);
            add(222L);
            add(333L);
        }};

        actualAccounts.forEach(e -> assertTrue(expected.contains(e)));
    }

    @Test
    void failGetAllAccountsByClientId() {
        long clientId = 1L;

        String filePath = "somePath";

        accountRepository = new FileAccountRepository(filePath);

        assertThrows(RuntimeException.class, () -> accountRepository.getAllAccountsByClientId(clientId));
    }


    @Test
    void updatePersonalAccount() throws IOException {
        String filePath = "src/main/resources/Accounts.txt";
        accountRepository = new FileAccountRepository(filePath);

        long clientId = 1L;
        long oldContractNumber = 111L;
        long newContractNumber = 123L;


        accountRepository.updateContractNumberByClientId(clientId, oldContractNumber, newContractNumber);
        Set<Long> actualAccounts = accountRepository.getAllAccountsByClientId(clientId);

        assertTrue(!actualAccounts.contains(oldContractNumber) && actualAccounts.contains(newContractNumber));

        accountRepository.updateContractNumberByClientId(clientId, newContractNumber, oldContractNumber);
    }

    @Test
    void failUpdatePersonalAccount() {
        long clientId = 1L;
        long oldContractNumber = 111L;
        long newContractNumber = 123L;

        String filePath = "somePath";

        accountRepository = new FileAccountRepository(filePath);

        assertThrows(RuntimeException.class,
                () -> accountRepository.updateContractNumberByClientId(clientId, oldContractNumber, newContractNumber));
    }

}
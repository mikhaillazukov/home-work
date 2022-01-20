package com.sbrf.reboot.generics;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GenericsTest {

    class DebitCard extends BankProduct {
        public DebitCard(Long clientId) {
            super(clientId);
        }
    }

    class CreditCard extends BankProduct {
        public CreditCard(Long clientId) {
            super(clientId);
        }
    }

    @Test
    void getClientIdByProductId() {
        Long clientId = 100L;
        Long creditCardId = 112L;
        Long debitCardId = 213L;

        ProductRepository<CreditCard> creditCardRepository = new ProductRepository<>();
        creditCardRepository.addProduct(creditCardId, new CreditCard(clientId));

        assertEquals(clientId, creditCardRepository.getClientIdByProductId(creditCardId));

        ProductRepository<DebitCard> debitCardRepository = new ProductRepository<>();
        debitCardRepository.addProduct(debitCardId, new DebitCard(clientId));

        assertEquals(clientId, debitCardRepository.getClientIdByProductId(debitCardId));
    }

    @Test
    void failGetClientIdByProductId() {
        Long clientId = 100L;
        Long creditCardId = 112L;

        ProductRepository<CreditCard> creditCardRepository = new ProductRepository<>();
        creditCardRepository.addProduct(creditCardId, new CreditCard(clientId));

        assertThrows(RuntimeException.class,
                () -> creditCardRepository.getClientIdByProductId(234L));
    }
}

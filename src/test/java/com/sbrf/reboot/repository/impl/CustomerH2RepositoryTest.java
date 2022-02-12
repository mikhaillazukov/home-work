package com.sbrf.reboot.repository.impl;

import com.sbrf.reboot.dto.Customer;
import com.sbrf.reboot.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerH2RepositoryTest {

    private static CustomerRepository customerRepository;

    @BeforeAll
    public static void before() {
        customerRepository = new CustomerH2Repository();
        clearCustomerRepository();
    }

    private static void clearCustomerRepository() {
        List<Customer> all = customerRepository.getAll();

        for (Customer customer : all) {
            customerRepository.deleteCustomer(customerRepository.getCustomerIdByEmail(customer.getEMail()));
        }
    }

    @Test
    void getAll() {
        boolean tomCreated = customerRepository.createCustomer("Tom", "tom@ya.ru");

        List<Customer> all = customerRepository.getAll();

        assertTrue(all.size() != 0);
    }

    @Test
    void createCustomer() {

        boolean mariaCreated = customerRepository.createCustomer("Maria", "maria_the_boss@ya.ru");

        assertTrue(mariaCreated);
    }

    @Test
    void createCustomerWithNotUniqueEmail() {

        boolean ninaCreatedSuccessfully = customerRepository.createCustomer("Nina", "nina12@ya.ru");

        boolean ninaCreatedFailed = customerRepository.createCustomer("Nina", "nina12@ya.ru");

        assertTrue(ninaCreatedSuccessfully);
        assertFalse(ninaCreatedFailed);
    }

    @Test
    void getExistingUserId() {

        boolean vovaCreated = customerRepository.createCustomer("Vova", "vova34@ya.ru");

        long vovaId = customerRepository.getCustomerIdByEmail("vova34@ya.ru");

        assertTrue(vovaId > 0L);
    }


    @Test
    void getNonExistingUserId() {

        long nonExistingUserId = customerRepository.getCustomerIdByEmail("usernotexists@mail.ru");

        assertEquals(-1L, nonExistingUserId);
    }

    @Test
    void deleteUser() {

        boolean sashaCreated = customerRepository.createCustomer("Sasha", "sasha17@ya.ru");

        List<Customer> all = customerRepository.getAll();

        boolean sashaDeleted = customerRepository.deleteCustomer(customerRepository.getCustomerIdByEmail("sasha17@ya.ru"));

        List<Customer> allWithoutSasha = customerRepository.getAll();

        assertTrue(sashaDeleted);
        assertTrue(all.size() != allWithoutSasha.size());
    }

}
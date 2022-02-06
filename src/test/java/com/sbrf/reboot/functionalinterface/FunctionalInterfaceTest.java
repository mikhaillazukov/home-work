package com.sbrf.reboot.functionalinterface;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.sbrf.reboot.utils.JSONUtils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FunctionalInterfaceTest {

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    static class SomeObject {
        private String objectName;
    }

    @FunctionalInterface
    interface ObjectToJsonFunction<T> {
        String applyAsJson(T t) throws JsonProcessingException;
    }

    static class ListConverter<T> {
        public List<String> toJsonsList(@NonNull List<T> someObjects, ObjectToJsonFunction<T> objectToJsonFunction) throws JsonProcessingException {
            List<String> result = new ArrayList<>();
            if (someObjects.isEmpty())
                throw new IllegalArgumentException("The list is empty");

            for (T object : someObjects)
                result.add(objectToJsonFunction.applyAsJson(object));

            return result;
        }
    }

    @Test
    public void successCallFunctionalInterface() throws JsonProcessingException {
        ListConverter<SomeObject> ListConverter = new ListConverter<>();

        ObjectToJsonFunction<SomeObject> objectToJsonFunction = someObject -> {
            return JSONUtils.toJSON(someObject);
        };

        List<String> strings = ListConverter.toJsonsList(
                Arrays.asList(
                        new SomeObject("Object-1"),
                        new SomeObject("Object-2")
                ),
                objectToJsonFunction
        );

        Assertions.assertTrue(strings.contains("{\"objectName\":\"Object-1\"}"));
        Assertions.assertTrue(strings.contains("{\"objectName\":\"Object-2\"}"));
    }

    @FunctionalInterface
    interface CurrencyConvertFunction<T> {
        T applyConversion(T t);
    }

    static class AccountsBalanceConverter<T> {
        public List<T> toAnotherCurrencyList(@NonNull List<T> accounts, CurrencyConvertFunction<T> currencyConvertFunction) {
            List<T> result = new ArrayList<>();
            if (accounts.isEmpty())
                throw new IllegalArgumentException("The list is empty");

            for (T accountBalance : accounts)
                result.add(currencyConvertFunction.applyConversion(accountBalance));

            return result;
        }
    }


    @Test
    public void successConversion(){
        AccountsBalanceConverter<BigDecimal> accountsBalanceConverter = new AccountsBalanceConverter<>();

        CurrencyConvertFunction<BigDecimal> rublesToUSD = rubles -> {
            return rubles.divide(BigDecimal.valueOf(76.05), MathContext.DECIMAL128).stripTrailingZeros();
        };

        CurrencyConvertFunction<BigDecimal> USDToRubles = USD -> {
            return USD.multiply(BigDecimal.valueOf(76.05), MathContext.DECIMAL128).stripTrailingZeros();
        };

        List<BigDecimal> accountsInRubles = Arrays.asList(
                BigDecimal.valueOf(2147890.90),
                BigDecimal.valueOf(3493290.32)
        );

        List<BigDecimal> accountsInUSD = accountsBalanceConverter.toAnotherCurrencyList(accountsInRubles, rublesToUSD);

        Assertions.assertEquals(
                accountsInRubles,
                accountsBalanceConverter.toAnotherCurrencyList(accountsInUSD, USDToRubles)
        );
    }

}

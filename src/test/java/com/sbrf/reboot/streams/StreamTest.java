package com.sbrf.reboot.streams;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StreamTest {

    /*
     * Отсортируйте коллекцию integers по возрастанию. Используйте Stream.
     */
    @Test
    public void sortedListStream() {
        List<Integer> integers = Arrays.asList(6, 9, 8, 3);

        List<Integer> expectedIntegers = Arrays.asList(3, 6, 8, 9);

        List<Integer> actualIntegers = integers.stream().sorted().collect(Collectors.toList()); //add code here

        assertEquals(expectedIntegers, actualIntegers);
    }

    /*
     * Отфильтруйте коллекцию integers.
     * В коллекции должны остаться только те числа, которые делятся без остатка на 2.  Используйте Stream.
     */
    @Test
    public void filteredListStream() {
        List<Integer> integers = Arrays.asList(6, 9, 8, 3);

        List<Integer> expectedIntegers = Arrays.asList(6, 8);

        List<Integer> actualIntegers = integers.stream().filter(i -> i % 2 == 0).collect(Collectors.toList()); //add code here

        assertEquals(expectedIntegers, actualIntegers);

    }

    /*
     * Отфильтруйте и отсортируйте коллекцию books.
     * Получите коллекцию, в которой будут только книги от автора "Maria", отсортированные по цене.
     * Используйте Stream.
     */
    @AllArgsConstructor
    @EqualsAndHashCode
    class Book {
        private String name;
        private String author;
        private BigDecimal price;
    }

    @Test
    public void sortedAndFilteredBooks() {
        List<Book> books = Arrays.asList(
                new Book("Trees", "Maria", new BigDecimal(900)),
                new Book("Animals", "Tom", new BigDecimal(500)),
                new Book("Cars", "John", new BigDecimal(200)),
                new Book("Birds", "Maria", new BigDecimal(100)),
                new Book("Flowers", "Tom", new BigDecimal(700))

        );
        List<Book> expectedBooks = Arrays.asList(
                new Book("Birds", "Maria", new BigDecimal(100)),
                new Book("Trees", "Maria", new BigDecimal(900))

        );

        List<Book> actualBooks = books.stream()
                .filter(book -> book.author == "Maria")
                .sorted(Comparator.comparing(book -> book.price))
                .collect(Collectors.toList()); //add code here

        assertEquals(expectedBooks, actualBooks);

    }

    /*
     * Получите измененную коллекцию contracts.
     * Получите коллекцию, в которой будет тот же набор строк, только у каждой строки появится префикс "M-".
     * Используйте Stream.
     */
    @Test
    public void modifiedList() {
        List<String> contracts = Arrays.asList("NCC-1-CH", "NCC-2-US", "NCC-3-NH");

        List<String> expectedContracts = Arrays.asList("M-NCC-1-CH", "M-NCC-2-US", "M-NCC-3-NH");

        List<String> actualContracts = contracts.stream().map(s -> "M-" + s).collect(Collectors.toList()); //add code here

        assertEquals(expectedContracts, actualContracts);

    }


    @Test
    public void multiplyFirstTwoElements() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);

        int expectedResult = 2;

        int actualResult = numbers.stream().limit(2).reduce((s1, s2) -> s1 * s2).get();

        assertEquals(expectedResult, actualResult);
    }


    @Test
    public void sumOfLastTwoElements() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);

        int expectedSum = 7;

        int actualSum = numbers.stream().skip(numbers.size()-2).reduce((s1, s2) -> s1 + s2).get();

        assertEquals(expectedSum, actualSum);
    }

    @Test
    public void maxElementOfListStream() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);

        int expectedMaxNumber = 4;

        Integer actualMaxNumber = numbers.stream().max(Integer::compareTo).get();

        assertEquals(expectedMaxNumber, actualMaxNumber);
    }


    @Test
    public void minElementOfListStream() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);

        int expectedMinNumber = 1;

        Integer actualMinNumber = numbers.stream().min(Integer::compareTo).get();

        assertEquals(expectedMinNumber, actualMinNumber);
    }
}

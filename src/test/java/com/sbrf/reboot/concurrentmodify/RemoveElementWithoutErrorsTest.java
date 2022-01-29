package com.sbrf.reboot.concurrentmodify;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RemoveElementWithoutErrorsTest {

    @Test
    public void successConcurrentModificationException() {

        List<Integer> list = new ArrayList() {{
            add(1);
            add(2);
            add(3);
        }};

        assertThrows(ConcurrentModificationException.class, () -> {

            for (Integer integer : list) {
                list.remove(1);
            }

        });

    }

    @Test
    public void successRemoveElementWithoutErrorsMethod1() {
        List<Integer> list = new ArrayList() {{
            add(1);
            add(2);
            add(3);
        }};

        List<Integer> elementsToRemove = new ArrayList<>();

        for(Integer value: list) {
            if (value == 2)
                elementsToRemove.add(value);
        }

        list.removeAll(elementsToRemove);

        assertEquals(Arrays.asList(1, 3), list);
    }

    @Test
    public void successRemoveElementWithoutErrorsMethod2() {
        List<Integer> list = new ArrayList() {{
            add(1);
            add(2);
            add(3);
        }};

        list.removeIf(value -> value == 2);

        assertEquals(Arrays.asList(1, 3), list);
    }

    @Test
    public void successRemoveElementWithoutErrorsMethod3() {
        List<Integer> list = new ArrayList() {{
            add(1);
            add(2);
            add(3);
        }};

        list = list.stream()
                .filter(value -> value != 2)
                .collect(Collectors.toList());

        assertEquals(Arrays.asList(1, 3), list);
    }

}

package com.beginsecure.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DataSorterTest {

    private DataSorter dataSorter;

    @BeforeEach
    void setUp() {
        dataSorter = new DataSorter();
    }

    @Test
    void removeDublicate() {
        String input = "Тест 123 Hello world";
        String expectedResult = "123 Hello Тест world";
        String result = dataSorter.removeDublicate(input);
        assertThat(result, equalTo(expectedResult));
    }

    @Test
    void testRemoveVowelsThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> dataSorter.removeVowels(null));
    }

    @ParameterizedTest
    @CsvSource({
            "доброго, дбрг",
            "morning, mrnng",
            "Have, Hv"
    })
    void removeVowels(String input, String expectedResult) {
        String result = dataSorter.removeVowels(input);
        assertThat(result, equalTo(expectedResult));
    }

    @Test
    void testRemoveVowelsHandlesNull() {
        DataSorter dataSorter = new DataSorter();
        assertThrows(IllegalArgumentException.class, () -> dataSorter.removeVowels(null));
    }

}


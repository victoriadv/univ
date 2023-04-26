package com.beginsecure.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.startsWith;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class TextParserTest {

    private TextParser textParser;

    @BeforeEach
    void setUp() {
        textParser = new TextParser();
    }

    @Test
    void parseText() {
        List<String> input = Arrays.asList("Тест! 123", "Hello, world!");
        String expectedResult = "Тест 123 Hello world";
        String result = textParser.parseText(input);
        assertThat(result, equalTo(expectedResult));
    }

    @Test
    void parseTextStartsWith() {
        List<String> input = Arrays.asList("Тест! 123", "Hello, world!");
        String result = textParser.parseText(input);
        assertThat(result, startsWith("Тест"));
    }

    @Test
    void parseTextEndsWith() {
        List<String> input = Arrays.asList("Тест! 123", "Hello, world!");
        String result = textParser.parseText(input);
        assertThat(result, endsWith("world"));
    }
    @Test
    void parseTextContainsDigits() {
        List<String> input = Arrays.asList("Тест! 123", "Hello, world!");
        String result = textParser.parseText(input);
        assertTrue(result.matches(".*\\d+.*"), "Result should contain digits");
    }

    @Test
    void parseTextDoesNotContainSpecialCharacters() {
        List<String> input = Arrays.asList("Тест! 123", "Hello, world!");
        String result = textParser.parseText(input);
        assertFalse(result.matches(".*[!@#$%^&*(),.?\":{}|<>].*"), "Result should not contain special characters");
    }

}

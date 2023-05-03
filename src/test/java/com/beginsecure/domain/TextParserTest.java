package com.beginsecure.domain;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;
import static org.hamcrest.Matchers.endsWith;


import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

public class TextParserTest {

    private TextParser textParser;

    @BeforeMethod
    public void setUp() {
        textParser = new TextParser();
    }

    @Test(groups = "GroupA")
    public void parseText() {
        List<String> input = Arrays.asList("Тест! 123", "Hello, world!");
        String expectedResult = "Тест 123 Hello world";
        String result = textParser.parseText(input);
        assertThat(result, equalTo(expectedResult));
    }

    @Test(groups = "GroupA")
    public void parseTextStartsWith() {
        List<String> input = Arrays.asList("Тест! 123", "Hello, world!");
        String result = textParser.parseText(input);
        assertThat(result, startsWith("Тест"));
    }

    @Test(groups = "GroupB")
    public void parseTextEndsWith() {
        List<String> input = Arrays.asList("Тест! 123", "Hello, world!");
        String result = textParser.parseText(input);
        assertThat(result, endsWith("world"));
    }

    @Test(groups = "GroupB")
    public void parseTextContainsDigits() {
        List<String> input = Arrays.asList("Тест! 123", "Hello, world!");
        String result = textParser.parseText(input);
        assertTrue(result.matches(".*\\d+.*"), "Result should contain digits");
    }

    @Test(groups = "GroupB")
    public void parseTextDoesNotContainSpecialCharacters() {
        List<String> input = Arrays.asList("Тест! 123", "Hello, world!");
        String result = textParser.parseText(input);
        assertFalse(result.matches(".*[!@#$%^&*(),.?\":{}|<>].*"), "Result should not contain special characters");
    }
}


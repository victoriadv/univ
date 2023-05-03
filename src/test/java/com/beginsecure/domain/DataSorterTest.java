package com.beginsecure.domain;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertThrows;

public class DataSorterTest {

    private DataSorter dataSorter;

    @BeforeMethod
    public void setUp() {
        dataSorter = new DataSorter();
    }

    @Test(groups = "GroupA")
    public void removeDublicate() {
        String input = "Тест 123 Hello world";
        String expectedResult = "123 Hello Тест world";
        String result = dataSorter.removeDublicate(input);
        assertThat(result, equalTo(expectedResult));
    }

    @Test(groups = "GroupA")
    public void testRemoveVowelsThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> dataSorter.removeVowels(null));
    }

    @DataProvider(name = "removeVowelsDataProvider")
    public Object[][] removeVowelsDataProvider() {
        return new Object[][]{
                {"доброго", "дбрг"},
                {"morning", "mrnng"},
                {"Have", "Hv"}
        };
    }

    @Test(groups = "GroupB", dataProvider = "removeVowelsDataProvider")
    public void removeVowels(String input, String expectedResult) {
        String result = dataSorter.removeVowels(input);
        assertThat(result, equalTo(expectedResult));
    }
}

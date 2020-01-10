package com.andrewkaraman.rtest.ui.CSV;

import com.andrewkaraman.rtest.ui.CSV.CSVConverter;

import org.junit.Test;

import org.junit.Assert;

import static org.junit.Assert.assertArrayEquals;

public class ConverterTest {

    private static final String[] STRINGS = {"a\tb\tc\td", " a \t b \t 1 2 ", "\"foo baar\"\t b"};

    private static final String[][] SEPARATED_ARRAY = {{"a", "b", "c", "d"}, {" a ", " b ", " 1 2 "}, {"\"foo baar\"", " b"}};

    @Test
    public void stringsToLineTest() {
        for (int i = 0; i < SEPARATED_ARRAY.length; i++) {
            Assert.assertEquals(STRINGS[i], CSVConverter.getFormattedString(SEPARATED_ARRAY[i]));
        }
    }

    @Test
    public void lineToArrayTest() {
        for (int i = 0; i < SEPARATED_ARRAY.length; i++) {
            assertArrayEquals(SEPARATED_ARRAY[i], CSVConverter.splitString(STRINGS[i]));
        }
    }
}

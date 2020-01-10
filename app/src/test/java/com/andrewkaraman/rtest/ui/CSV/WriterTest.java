package com.andrewkaraman.rtest.ui.CSV;

import com.andrewkaraman.rtest.ui.CSV.CSVWriter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;

public class WriterTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void testBufferedWriter() throws IOException {
        CSVWriter writer = new CSVWriter();
        assertNull(writer.getBufferWriter());
        writer.open(folder + "test.txt");
        assertNotNull(writer.getBufferWriter());
    }

    @Test
    public void testBufferedWriterWithConvertion() throws IOException {
        CSVWriter writer = new CSVWriter();
        writer.open(folder + "test.txt");

        String str = "AbCdEfGhIjKlMnOpQrStUvWxYz";
        writer.write(str, "X");
        writer.getBufferWriter().flush();

        BufferedReader _bufferedReader = new BufferedReader(new FileReader(folder + "test.txt"));
        assertEquals("AbCdEfGhIjKlMnOpQrStUvWxYz\tX", _bufferedReader.readLine());
    }
}

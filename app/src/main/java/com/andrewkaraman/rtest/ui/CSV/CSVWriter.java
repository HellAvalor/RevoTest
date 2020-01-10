package com.andrewkaraman.rtest.ui.CSV;

import androidx.annotation.VisibleForTesting;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter {

    private BufferedWriter bufferedWriter = null;

    public void open(String fileName) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        bufferedWriter = new BufferedWriter(fileWriter);
    }

    public void write(String... columns) throws IOException {
        bufferedWriter.write(CSVParser.getFormattedString(columns));
    }

    public void write(String line) throws IOException {
        bufferedWriter.write(line);
    }

    public void close(){
        if (bufferedWriter != null)
        {
            try {
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    BufferedWriter getBufferWriter(){
        return bufferedWriter;
    }
}

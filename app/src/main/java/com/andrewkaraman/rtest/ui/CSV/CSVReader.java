package com.andrewkaraman.rtest.ui.CSV;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {

    private BufferedReader _bufferedReader = null;

    public void open(String fileName) throws FileNotFoundException {
        _bufferedReader = new BufferedReader(new FileReader(fileName));
    }

    public String[] read() {
        String[] splittedLine = null;
        try {
            String line = _bufferedReader.readLine();
            if (line != null) {
                splittedLine = CSVConverter.splitString(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return splittedLine;
    }

    public String readLine() {
        String line = null;
        try {
            line = _bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    public void close() {
        if (_bufferedReader != null) {
            try {
                _bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

package com.andrewkaraman.rtest.ui.CSV;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {

    private BufferedReader bufferedReader = null;
    private boolean isStreamOpen;

    public void open(String fileName) throws FileNotFoundException {
        bufferedReader = new BufferedReader(new FileReader(fileName));
        isStreamOpen = true;
    }

    public String[] read() {
        String[] splittedLine = null;
        try {
            String line = bufferedReader.readLine();
            if (line != null) {
                splittedLine = CSVParser.splitString(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return splittedLine;
    }

    public String readLine() {
        String line = null;
        try {
            line = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    public void close() {
        if (bufferedReader != null) {
            try {
                bufferedReader.close();
                isStreamOpen = false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public BufferedReader getBufferReader() {
        return bufferedReader;
    }

    public boolean isStreamOpen(){
        return isStreamOpen;
    }

}

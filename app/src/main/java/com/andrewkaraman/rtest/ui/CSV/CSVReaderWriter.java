package com.andrewkaraman.rtest.ui.CSV;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CSVReaderWriter {

    private String separator = "\t";

    private BufferedReader _bufferedReader = null;
    private BufferedWriter _bufferedWriter = null;

    public enum Mode {
        Read (1), Write(2);

        private int _mode;
        Mode(int mode) {
            this._mode = mode;
        }
        public int getMode() {
            return _mode;
        }
    }

    public void open(String fileName, Mode mode) throws Exception {
        if (mode == Mode.Read)
        {
            _bufferedReader = new BufferedReader(new FileReader(fileName));
        }
        else if (mode == Mode.Write)
        {
            FileWriter fileWriter = new FileWriter(fileName);
            _bufferedWriter = new BufferedWriter(fileWriter);
        }
        else
        {
            throw new Exception("Unknown file mode for " + fileName);
        }
    }

    public void write(String... columns) throws IOException {

        StringBuilder outPut = new StringBuilder();

        for (int i = 0; i < columns.length; i++)
        {
            outPut.append(columns[i]);
            if ((columns.length - 1) != i)
            {
                outPut.append(separator);
            }
        }

        writeLine(outPut.toString());
    }

    public boolean read(String[] columns) throws IOException {
        final int FIRST_COLUMN = 0;
        final int SECOND_COLUMN = 1;

        String line;
        String[] splitLine;

        line = readLine();
        splitLine = line.split(separator);

        if (splitLine.length == 0)
        {
            columns[0] = null;
            columns[1] = null;

            return false;
        }

     else
    {
        columns[0] = splitLine[FIRST_COLUMN];
        columns[1] = splitLine[SECOND_COLUMN];

        return true;
    }
}
    public boolean read(String column1, String column2) throws IOException {

        final int FIRST_COLUMN = 0;
        final int SECOND_COLUMN = 1;

        String line;
        String[] splitLine;

        line = readLine();

        if (line == null)
        {
            return false;
        }

        splitLine = line.split(separator);

        if (splitLine.length == 0)
        {
            column1 = null;
            column2 = null;

            return false;
        }
        else
        {
            column1 = splitLine[FIRST_COLUMN];
            column2 = splitLine[SECOND_COLUMN];

            return true;
        }
    }

    private void writeLine(String line) throws IOException {
        _bufferedWriter.write(line);
    }

    private String readLine() throws IOException {
        return _bufferedReader.readLine();
    }

    public void close() throws IOException {
        if (_bufferedWriter != null)
        {
            _bufferedWriter.close();
        }

        if (_bufferedWriter != null)
        {
            _bufferedWriter.close();
        }
    }
}

package com.andrewkaraman.rtest.ui.CSV;

import timber.log.Timber;

public class CSVParser {

    private static String defaultSeparator = "\t";
    public static String getFormattedString(String[] columns) {
        return getFormattedString(columns, null);
    }
    public static String getFormattedString(String[] columns, String customSeparator) {

        StringBuilder outPut = new StringBuilder();

        for (int i = 0; i < columns.length; i++) {
            outPut.append(columns[i]);
            if ((columns.length - 1) != i) {
                outPut.append(customSeparator == null ? defaultSeparator : customSeparator);
            }
        }
        Timber.d("getFormattedString: " + outPut.toString());
        return outPut.toString();
    }

    public static String[] splitString(String line) {
        return splitString(line, null);
    }

    public static String[] splitString(String line, String customSeparator) {
        return line.split(customSeparator == null ? defaultSeparator : customSeparator);
    }
}

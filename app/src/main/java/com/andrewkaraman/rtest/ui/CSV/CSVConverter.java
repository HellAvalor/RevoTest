package com.andrewkaraman.rtest.ui.CSV;

import android.util.Log;

import timber.log.Timber;

public class CSVConverter {

    private static String separator = "\t";

    public static String getFormattedString(String... columns){

        StringBuilder outPut = new StringBuilder();

        for (int i = 0; i < columns.length; i++)
        {
            outPut.append(columns[i]);
            if ((columns.length - 1) != i)
            {
                outPut.append(separator);
            }
        }
        Timber.d("getFormattedString: " + outPut.toString());
        return outPut.toString();
    }

    public static String[] splitString(String line){
        return line.split(separator);
    }
}

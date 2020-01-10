package com.andrewkaraman.rtest.ui.CSV;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.net.URL;

import static junit.framework.Assert.assertFalse;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class ReaderTest {

    @Test
    public void testBufferReadingOpen() {
        CSVReader reader = new CSVReader();
        try {
            reader.open(getFileFromPath(this, "contacts.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assertNotNull(reader.getBufferReader());
    }

    @Test
    public void testReadingFirstLine() {
        CSVReader reader = new CSVReader();
        try {
            reader.open(getFileFromPath(this, "contacts.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assertEquals("Shelby Macias\t3027 Lorem St.|Kokomo|Hertfordshire|L9T 3D5|England\t1 66 890 3865-9584\tet@eratvolutpat.ca",reader.readLine());
    }

    @Test
    public void testReading3Lines() {
        CSVReader reader = new CSVReader();
        try {
            reader.open(getFileFromPath(this, "contacts.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        reader.readLine();
        reader.readLine();
        assertEquals("Noelani Ward\t637-911 Mi Rd.|Monrovia|MB|M5M 6SC|Scotland\t1 15 373 1666-1277\tadipiscing@neque.edu",reader.readLine());
    }


    @Test
    public void testClosingOfStream() {
        CSVReader reader = new CSVReader();
        try {
            reader.open(getFileFromPath(this, "contacts.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        reader.close();
        assertFalse(reader.isStreamOpen());
    }

    private static String getFileFromPath(Object obj, String fileName) {
        URL resource = obj.getClass().getClassLoader().getResource(fileName);
        return resource.getPath();
    }
}

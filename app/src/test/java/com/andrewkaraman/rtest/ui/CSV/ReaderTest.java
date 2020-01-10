package com.andrewkaraman.rtest.ui.CSV;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.net.URL;

public class ReaderTest {
    @Test
    public void fileObjectShouldNotBeNull() {
        File file = getFileFromPath(this, "contacts.csv");
        Assert.assertNotNull(file);
    }

    private static File getFileFromPath(Object obj, String fileName) {
      //  ClassLoader classLoader = obj.getClass().getClassLoader();
        URL resource = obj.getClass().getClassLoader().getResource(fileName);
        return new File(resource.getPath());
    }
}

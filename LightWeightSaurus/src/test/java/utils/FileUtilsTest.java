package utils;

import org.junit.Test;

import static org.junit.Assert.*;
public class FileUtilsTest {


    @Test
    public void loadAsString() throws Exception {
        String string = FileUtils.loadAsString("/test/Test.txt");
        assertEquals("This is test file", string);
    }
}

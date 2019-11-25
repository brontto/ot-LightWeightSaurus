package utility;

import org.junit.Test;

import static org.junit.Assert.*;
public class FileUtilsTest {


    @Test
    public void loadAsString() throws Exception {
        String string = FileUtils.loadAsString("/Test/Test.txt");
        assertEquals("This is test file", string);
    }
}

package utils;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class FileUtilsTest {

    @Test
    public void start() {
        FileUtils utils = new FileUtils();
        assertNotNull(utils);
    }

    @Test
    public void loadAllLines() throws Exception {
        List<String> lines = FileUtils.readAllLines("/models/cube.obj");
        assertNotNull(lines);
    }

    @Test
    public void loadAsString() throws Exception {
        String string = FileUtils.loadAsString("/test/Test.txt");
        assertEquals("This is test file", string);
    }
}

package utils;

import java.io.InputStream;
import java.util.Scanner;

public class FileUtils {
    public static String loadAsString(String filePath) throws Exception {
        String result;
        try (InputStream in = Class.forName(FileUtils.class.getName()).getResourceAsStream(filePath);
             Scanner scanner = new Scanner(in, "UTF-8")) {
            result = scanner.useDelimiter("\\A").next();
        }
        return result;
    }
}

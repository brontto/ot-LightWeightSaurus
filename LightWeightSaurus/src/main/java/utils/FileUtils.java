package utils;

import java.io.InputStream;
import java.util.Scanner;

public class FileUtils {

    /**
     * Lukee tiedoston merkkijonoksi polusta.
     *
     * @param filePath Tiedostopolku.
     * @return Tiedosto merkkijonona.
     * @throws Exception Poikkeus jos tiedostoa ei löydy tai voida lukea.
     */
    public static String loadAsString(String filePath) throws Exception {
        String result;
        try (InputStream in = Class.forName(FileUtils.class.getName()).getResourceAsStream(filePath);
             Scanner scanner = new Scanner(in, "UTF-8")) {
            result = scanner.useDelimiter("\\A").next();
        }
        return result;
    }
}

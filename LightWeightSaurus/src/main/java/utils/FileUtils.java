package utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileUtils {

    /**
     * Lukee tiedoston merkkijonoksi polusta.
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


    public static List<String> readAllLines(String filePath) throws Exception {
        List<String> list = new ArrayList<>();
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
            Class.forName(FileUtils.class.getName()).getResourceAsStream(filePath)))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                list.add(line);
            }
        }

        return list;
    }
}

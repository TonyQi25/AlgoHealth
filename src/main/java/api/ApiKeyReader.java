package api;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ApiKeyReader {

    public static String genMyApiKey(String textFile) {
        try {
            Scanner envVar = new Scanner(new File(textFile));
            return envVar.nextLine();
        } catch (FileNotFoundException ev) {
            return "File not found";
        }
    }
}

package api;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ApiKeyReader {

    /**
     * Returns the API key from a given file name. The API key must be on the first line of the file.
     *
     * @param textFile the file name containing the API key for the FDC api.
     */
    public static String genMyApiKey(String textFile) {
        String key;

        try {
            final Scanner envVar = new Scanner(new File(textFile));
            key = envVar.nextLine();
        }
        catch (FileNotFoundException ex) {
            key = "File not found";
        }

        return key;
    }
}

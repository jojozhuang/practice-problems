package johnny.problem;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class KeyValueStoreExample {
    private static final String PREFIX_INPUT_FILE = "input";
    private static final String PREFIX_OUTPUT_FILE = "output";

    public static void main(String[] args) {

        try {
            ClassLoader classLoader = KeyValueStoreExample.class.getClassLoader();

            for (int i = 1; i <= 2; i++) {

                // Create VersionedStore object
                KeyValueStore kvs = new KeyValueStore();
                Path path = Paths.get("files", PREFIX_INPUT_FILE + i + ".txt");
                InputStream inputStream = classLoader.getResourceAsStream(path.toString());
                System.setIn(inputStream);

                // Set system.out
                //Path output = Paths.get("files", PREFIX_OUTPUT_FILE + i + ".txt");
                //File outputFile = output.toFile();
                //System.setOut(new PrintStream(outputFile));

                // Read from stdin
                Scanner sc = new Scanner(System.in);
                while (sc.hasNextLine()) { // check by line
                    String operator = sc.next();
                    // Check operation first
                    if (operator.equals("PUT")) {
                        String key = sc.next();
                        String value = sc.next();
                        kvs.put(key, value);
                    } else { // the GET case
                        String key = sc.next();
                        Integer version = null;
                        // Check if version is specified
                        if (sc.hasNextInt()) {
                            version = sc.nextInt();
                        }
                        if (version == null) {
                            kvs.get(key);
                        } else {
                            kvs.get(key, version);
                        }
                    }
                }
                sc.close();
                System.out.println();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

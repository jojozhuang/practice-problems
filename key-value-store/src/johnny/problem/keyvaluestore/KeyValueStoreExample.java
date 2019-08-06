package johnny.problem.keyvaluestore;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class KeyValueStoreExample {
    private static final String PREFIX_INPUT_FILE = "input";
    private static final String PREFIX_OUTPUT_FILE = "output";

    public static void main(String[] args) {

        try {
            String currentDir = System.getProperty("user.dir");

            for (int i = 1; i <= 2; i++) {
                // Create VersionedStore object
                KeyValueStore kvs = new KeyValueStore();

                // Set system.io
                Path path = Paths.get(currentDir, "files", PREFIX_INPUT_FILE + i + ".txt");
                File file = path.toFile();
                System.setIn(new FileInputStream(file));

                // Set system.out
                Path output = Paths.get(currentDir, "files", PREFIX_OUTPUT_FILE + i + ".txt");
                File outputFile = output.toFile();
                System.setOut(new PrintStream(outputFile));

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
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

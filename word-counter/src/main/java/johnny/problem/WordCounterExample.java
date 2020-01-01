package johnny.problem;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class WordCounterExample {
    private static final String PREFIX_INPUT_FILE = "input";

    public static void main(String[] args) {

        for (int i = 1; i <= 2; i++) {
            String filename = PREFIX_INPUT_FILE + i + ".txt";
            System.out.println("Processing: " +  filename);
            List<String> list = read(filename);

            WordCounter wordCounter = new WordCounter();
            SortedMap<String, Integer> map = wordCounter.process(list);
            write(map);
        }
    }

    public static List<String> read(String filename) {
        List<String> list = new ArrayList<>();

        ClassLoader classLoader = WordCounterExample.class.getClassLoader();
        Path path = Paths.get("files", filename);
        try (InputStream inputStream = classLoader.getResourceAsStream(path.toString())) {
            System.setIn(inputStream);
            Scanner sc = new Scanner(System.in);
            while (sc.hasNext()) {
                list.add(sc.next());
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void write(SortedMap<String, Integer> map) {
        if (map == null || map.size() == 0) {
            return;
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    /*
        String currentDir = System.getProperty("user.dir");

        for (int i = 1; i <= 2; i++) {
            // Set system.io
            Path path = Paths.get(currentDir, "files", PREFIX_INPUT_FILE + i + ".txt");
            File file = path.toFile();
            System.setIn(new FileInputStream(file));

            // Set system.out
            Path output = Paths.get(currentDir, "files", PREFIX_OUTPUT_FILE + i + ".txt");
            File outputFile = output.toFile();
            System.setOut(new PrintStream(outputFile));

            WordCounter.main(null);
        }*/
}

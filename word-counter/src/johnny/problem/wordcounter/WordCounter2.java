package johnny.problem.wordcounter;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class WordCounter2 {
    private static final String INPUT_FILE = "words222.txt";
    private static final String OUTPUT_FILE = "output.txt";

    public static void main(String[] args) {
        try {
            List<String> list = read();
            SortedMap<String, Integer> map = process(list);
            write(map);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public static List<String> read() throws IOException {
        String currentDir = System.getProperty("user.dir");
        Path path = Paths.get(currentDir, "java-mapreduce", "files", INPUT_FILE);
        File file = path.toFile();

        // read
        Scanner sc = new Scanner(file);
        List<String> list = new ArrayList<>();

        while (sc.hasNext()) {
            list.add(sc.next());
        }
        sc.close();

        return list;
    }

    public static SortedMap<String, Integer> process(List<String> words) {
        if (words == null || words.size() == 0) {
            return null;
        }

        SortedMap<String, Integer> map = new TreeMap<>();

        for (String word : words) {
            if (!map.containsKey(word)) {
                map.put(word, 1);
            } else {
                map.put(word, map.get(word) + 1);
            }
        }
        return map;
    }

    public static void write(SortedMap<String, Integer> map) throws IOException {
        if (map == null || map.size() == 0) {
            return;
        }

        String currentDir = System.getProperty("user.dir");

        // write
        Path output = Paths.get(currentDir, "java-mapreduce", "files", OUTPUT_FILE);
        File outputFile = output.toFile();

        BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            bw.write(entry.getKey() + " " + entry.getValue());
            bw.newLine();
        }

        bw.close();
    }
}

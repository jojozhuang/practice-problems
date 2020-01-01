package johnny.problem;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordRankExample {
    private static final String INPUT_FILE = "input.txt";
    private static final String PREFIX_FILE = "prefix.txt";
    private static final String OUTPUT_FILE = "output.txt";

    public static void main(String args[]) throws Exception {

        ClassLoader classLoader = WordRankExample.class.getClassLoader();

        // Get words from file
        List<Word> words = new ArrayList<>();
        Path path = Paths.get("files", INPUT_FILE);
        try (InputStream inputStream = classLoader.getResourceAsStream(path.toString())) {
            System.setIn(inputStream);
            Scanner sc = new Scanner(System.in);
            while (sc.hasNextLine()) {
                words.add(new Word(sc.next(), sc.nextInt()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create Work Rank object
        WordRank wr = new WordRank(words);

        // Get prefixes from file
        List<String> prefixes = new ArrayList<>();
        path = Paths.get("files", PREFIX_FILE);
        try (InputStream inputStream = classLoader.getResourceAsStream(path.toString())) {
            System.setIn(inputStream);
            Scanner sc = new Scanner(System.in);
            while (sc.hasNext()) {
                prefixes.add(sc.next());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Search
        for (String pre : prefixes) {
            System.out.println(pre + ":");
            List<Word> list = wr.search(pre);
            for (Word word : list) {
                System.out.println(word.name + " " + word.rank);
            }
            System.out.println();
        }
    }
}
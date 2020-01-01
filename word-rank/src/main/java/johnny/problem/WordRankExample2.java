package johnny.problem;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class WordRankExample2 {
    private static final String INPUT_FILE = "files/input.txt";
    private static final String PREFIX_FILE = "files/prefix.txt";
    private static final String OUTPUT_FILE = "files/output.txt";

    public static void main(String args[]) throws Exception {
        String currentDir = System.getProperty("user.dir");

        // Get words from file
        List<Word> words = new ArrayList<>();
        // Set system.io
        Path path = Paths.get(currentDir, "files", INPUT_FILE);
        File file = path.toFile();
        System.setIn(new FileInputStream(file));
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            words.add(new Word(sc.next(), sc.nextInt()));
        }

        // Create Work Rank object
        WordRank wr = new WordRank(words);

        // Get prefixes from file
        List<String> prefixes = new ArrayList<>();
        path = Paths.get(currentDir, "files", PREFIX_FILE);
        file = path.toFile();
        System.setIn(new FileInputStream(file));
        sc = new Scanner(System.in);
        while (sc.hasNext()) {
            prefixes.add(sc.next());
        }
        sc.close();

        // Set system.out
        Path output = Paths.get(currentDir, "files", OUTPUT_FILE);
        File outputFile = output.toFile();
        System.setOut(new PrintStream(outputFile));

        // Search
        for (String pre : prefixes) {
            System.out.println(pre + ":");
            List<Word> list = wr.search(pre);
            Collections.sort(list, (a, b)->a.rank - b.rank);
            for (Word word : list) {
                System.out.println(word.name + " " + word.rank);
            }
            System.out.println();
        }
    }
}
package johnny.problem.wordcounter.test;

import johnny.problem.wordcounter.WordCounter;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WordCounterTest {

    private static final String PREFIX_INPUT_FILE = "input";
    private static final String PREFIX_OUTPUT_FILE = "output";

    @Test
    public void testWordCounter() throws IOException {
        System.out.println("testWordCounter");

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
        }
    }
}

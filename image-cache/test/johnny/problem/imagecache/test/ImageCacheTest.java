package johnny.problem.imagecache.test;

import johnny.problem.imagecache.ImageCache;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageCacheTest {
    private static final String INPUT_FILE = "images.txt";
    private static final String OUTPUT_FILE = "output.txt";

    @Test
    public void tesImageCache() throws IOException {
        System.out.println("tesImageCache");

        String currentDir = System.getProperty("user.dir");

        // Set system.io
        Path path = Paths.get(currentDir, "files", INPUT_FILE);
        File file = path.toFile();
        System.setIn(new FileInputStream(file));

        // Set system.out
        Path output = Paths.get(currentDir, "files", OUTPUT_FILE);
        File outputFile = output.toFile();
        System.setOut(new PrintStream(outputFile));

        ImageCache.main(null);
    }
}

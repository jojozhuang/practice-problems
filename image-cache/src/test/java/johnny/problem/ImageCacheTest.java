package johnny.problem;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ImageCacheTest {
    private static final String INPUT_FILE = "images.txt";

    @Test
    public void testImageCache() {
        System.out.println("testImageCache");

        int capacity = 0;
        int quantity = 0;
        List<String> urls = new ArrayList<>();

        ClassLoader classLoader = ImageCacheExample.class.getClassLoader();
        Path path = Paths.get("files", INPUT_FILE);
        try (InputStream inputStream = classLoader.getResourceAsStream(path.toString())) {
            System.setIn(inputStream);
            Scanner sc = new Scanner(System.in);
            capacity = sc.nextInt();
            quantity = sc.nextInt();
            while (sc.hasNext()) {
                urls.add(sc.next());
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ImageCache ic = new ImageCache();
        try {
            ic.process(urls, capacity, quantity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ic.print();
    }
}

package johnny.problem;

import org.junit.Test;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class WordCounterTest {
    @Test
    public void testWordCounter() {
        System.out.println("testWordCounter");

        List<String> list = read("input1.txt");
        WordCounter wordCounter = new WordCounter();
        SortedMap<String, Integer> map = wordCounter.process(list);
        assertEquals(3, map.size());
        assertEquals(3, (int)map.get("hello"));
        assertEquals(1, (int)map.get("howdy"));
        assertEquals(2, (int)map.get("world"));

        List<String> list2 = read("input2.txt");
        SortedMap<String, Integer> map2 = wordCounter.process(list2);
        assertEquals(6, map2.size());
        assertEquals(1, (int)map2.get("apple"));
        assertEquals(1, (int)map2.get("banana"));
        assertEquals(7, (int)map2.get("honey"));
        assertEquals(1, (int)map2.get("mango"));
        assertEquals(1, (int)map2.get("orange"));
        assertEquals(6, (int)map2.get("world"));
    }

    private List<String> read(String filename) {
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
}

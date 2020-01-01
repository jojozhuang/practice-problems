package johnny.problem;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class WordRankTest {
    @Test
    public void testWordRank() {
        System.out.println("testWordRank");

        List<Word> words = new ArrayList<>();
        words.add(new Word("hello", 6));
        words.add(new Word("world", 10));
        words.add(new Word("wide", 3));
        words.add(new Word("hell", 4));
        words.add(new Word("worldwide", 7));
        words.add(new Word("lyft", 20));

        WordRank wr = new WordRank(words);
        List<Word> res1 = wr.search("hell");
        assertEquals(2, res1.size());
        assertEquals("hell", res1.get(0).name);
        assertEquals(4, res1.get(0).rank);
        assertEquals("hello", res1.get(1).name);
        assertEquals(6, res1.get(1).rank);

        List<Word> res2 = wr.search("world");
        assertEquals(2, res2.size());
        assertEquals("worldwide", res2.get(0).name);
        assertEquals(7, res2.get(0).rank);
        assertEquals("world", res2.get(1).name);
        assertEquals(10, res2.get(1).rank);
    }
}

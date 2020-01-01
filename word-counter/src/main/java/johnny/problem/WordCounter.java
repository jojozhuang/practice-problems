package johnny.problem;

import java.util.*;

public class WordCounter {
    public SortedMap<String, Integer> process(List<String> words) {
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
}

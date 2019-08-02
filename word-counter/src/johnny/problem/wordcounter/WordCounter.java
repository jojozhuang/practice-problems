package johnny.problem.wordcounter;

import java.util.*;

public class WordCounter {
    public static void main(String[] args) {
        List<String> list = read();
        SortedMap<String, Integer> map = process(list);
        write(map);
    }

    public static List<String> read() {
        List<String> list = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
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

    public static void write(SortedMap<String, Integer> map) {
        if (map == null || map.size() == 0) {
            return;
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}

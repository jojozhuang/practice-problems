package johnny.problem.wordrank;

import java.util.*;
import java.util.stream.Collectors;

public class WordRank {
    private Trie trie;

    public WordRank(List<Word> words) {
        trie = new Trie();
        for (Word word : words) {
            trie.insert(word.name, word.rank);
        }
    }

    public List<Word> search(String prefix) {
        return trie.searchWords(prefix).stream().sorted((w1, w2) -> w1.rank - w2.rank).collect(Collectors.toList());
    }
}

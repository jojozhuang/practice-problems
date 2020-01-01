package johnny.problem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Trie {
    class TrieNode {
        public Map<Character, TrieNode> children;
        public int rank; // only valid when leaf = true
        public boolean leaf;

        public TrieNode() {
            children = new HashMap<>();
            leaf = false;
        }
    }

    private TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public TrieNode getRoot() {
        return this.root;
    }

    // Return true if the word is in trie
    public boolean search(String word) {
        TrieNode tn = searchNode(word);
        if (tn != null && tn.leaf) {
            return true;
        } else {
            return false;
        }
    }

    // Return true if there is any word in trie that starts with the given prefix
    public boolean startsWith(String prefix) {
        if (searchNode(prefix) == null) {
            return false;
        } else {
            return true;
        }
    }

    // Return true if there is any word in trie that starts with the given prefix
    public List<Word> searchWords(String prefix) {
        TrieNode current = root;
        StringBuilder sb = new StringBuilder();
        List<Word> list = new ArrayList<>();

        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            if (!current.children.containsKey(ch)) {
                return null;
            } else {
                sb.append(ch);
                current = current.children.get(ch);
            }
        }

        dfs(current, sb.toString(), list);

        return list;
    }

    private void dfs(TrieNode node, String prefix, List<Word> list) {
        if (node.leaf) {
            list.add(new Word(prefix, node.rank));
        }

        for (Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
            dfs(entry.getValue(), prefix + entry.getKey(), list);
        }
    }

    private TrieNode searchNode(String str) {
        TrieNode current = root;

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (current.children.containsKey(ch)) {
                current = current.children.get(ch);
            } else {
                return null;
            }
        }

        return current;
    }

    // Insert a word into trie
    public void insert(String word, int rank) {
        TrieNode current = root;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!current.children.containsKey(ch)) {
                current.children.put(ch, new TrieNode());
            }
            current = current.children.get(ch);
        }

        current.rank = rank;
        current.leaf = true;
    }

    public boolean delete(String word) {
        TrieNode current = root;
        TrieNode lastBranchNode = null;
        Character lastBrachChar = null;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (current.children.containsKey(ch)) {
                if (current.children.size() > 1) {
                    lastBranchNode = current;
                    lastBrachChar = ch;
                }
                current = current.children.get(ch);
            } else {
                // word not found
                return false;
            }
        }

        if (current.children.size() > 0) {
            // case 1: The to-be deleted word is prefix of another long word in trie.
            current.leaf = false;
            return true;
        }

        if (lastBranchNode != null) {
            // case 2: The to-be deleted word has other words as prefix
            lastBranchNode.children.remove(lastBrachChar);
            return true;
        } else {
            // case 3: The to-be deleted word present as unique word
            root.children.remove(word.charAt(0));
            return true;
        }
    }
}

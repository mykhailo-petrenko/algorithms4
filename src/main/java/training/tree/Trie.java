package training.tree;

/**
 * Implement Trie (Prefix Tree)
 * ============================
 *
 * Implement a trie with insert, search, and startsWith methods.
 *
 * Example:
 * ```
 * Trie trie = new Trie();
 *
 * trie.insert("apple");
 * trie.search("apple");   // returns true
 * trie.search("app");     // returns false
 * trie.startsWith("app"); // returns true
 * trie.insert("app");
 * trie.search("app");     // returns true
 * ```
 *
 * Note:
 *
 * You may assume that all inputs are consist of lowercase letters a-z.
 * All inputs are guaranteed to be non-empty strings.
 */
public class Trie {
    public static int R = 26;
    public static int shift = "a".charAt(0);

    public static class Node {
        boolean val;
        Node[] child;

        Node() {
            this.val = false;
            this.child = new Node[R];
        }
    }

    private Node root;

    /** Initialize your data structure here. */
    public Trie() {
        root = new Node();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        insert(root, word, 0);
    }

    public static void insert(Node node, String word, int index) {
        int s = (int)word.charAt(index) - shift;
        boolean end = word.length() == (index + 1);

        if (node.child[s] == null) {
            node.child[s] = new Node();
        }

        if (end) {
            node.child[s].val = true;
        } else {
            insert(node.child[s], word, index + 1);
        }
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        return search(root, word, 0);
    }

    public static boolean search(Node node, String word, int index) {
        int s = (int)word.charAt(index) - shift;
        boolean end = word.length() == (index + 1);

        if (node.child[s] == null) {
            return false;
        }

        if (end) {
            return node.child[s].val;
        } else {
            return search(node.child[s], word, index + 1);
        }
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        return startsWith(root, prefix, 0);
    }

    public static boolean startsWith(Node node, String word, int index) {
        int s = (int)word.charAt(index) - shift;
        boolean end = word.length() == (index + 1);

        if (node.child[s] == null) {
            return false;
        }

        if (end) {
            return true;
        } else {
            return startsWith(node.child[s], word, index + 1);
        }
    }
}

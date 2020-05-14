package training.tree;

import org.junit.Assert;
import org.junit.Test;

public class TrieTests {
    @Test()
    public void smokeTest() {
        Trie trie = new Trie();

        trie.insert("apple");

        Assert.assertTrue(
            trie.search("apple")
        );

        Assert.assertFalse(
            trie.search("app")
        );

        Assert.assertTrue(
            trie.startsWith("app")
        );

        trie.insert("app");

        Assert.assertTrue(
            trie.search("app")
        );
    }

    @Test()
    public void smoke2() {
        Trie trie = new Trie();

        Assert.assertFalse(
            trie.search("apple")
        );

        trie.insert("apple");
        trie.insert("banana");
        trie.insert("app");

        Assert.assertTrue(
            trie.search("apple")
        );
        Assert.assertTrue(
            trie.search("banana")
        );
        Assert.assertTrue(
            trie.search("app")
        );

        Assert.assertFalse(
            trie.search("ban")
        );
        Assert.assertTrue(
            trie.startsWith("a")
        );

        Assert.assertTrue(
            trie.startsWith("a")
        );
        Assert.assertTrue(
            trie.startsWith("b")
        );
        Assert.assertTrue(
            trie.startsWith("ban")
        );

        Assert.assertFalse(
            trie.startsWith("c")
        );
        Assert.assertFalse(
            trie.startsWith("nana")
        );
    }

    @Test
    public void borders() {
        Trie trie = new Trie();

        trie.insert("azazaz");
        trie.insert("zxc");
        trie.insert("zazaz");
        trie.insert("aaaaaaa");
        trie.insert("zzzzzzz");

        Assert.assertTrue(
            trie.startsWith("aza")
        );
        Assert.assertTrue(
            trie.startsWith("zz")
        );
        Assert.assertTrue(
            trie.startsWith("aa")
        );
    }
}

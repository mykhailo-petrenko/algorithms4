package searching.st;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

// @TODO: Update test cases for Red-Black BST
public class STRedBlackBSTTest {
    private STRedBlackBST<String, Integer> st;

    @Before
    public void beforeEach() {
        st = new STRedBlackBST<>();
    }

    @Test
    public void smokeTest() {
        Assert.assertEquals(0, st.size());
        st.put("b", 2);
        Assert.assertEquals(1, st.size());
        st.put("a", 1);
        Assert.assertEquals(2, st.size());
        st.put("c", 3);
        Assert.assertEquals(3, st.size());

        Assert.assertEquals(1, (int)st.get("a"));
        Assert.assertEquals(2, (int)st.get("b"));
        Assert.assertEquals(3, (int)st.get("c"));
    }

    @Test
    public void smokeTestForEmptyTree() {
        Assert.assertEquals(0, st.size());

        Assert.assertNull(st.get("a"));
        st.delete("b");
    }

    @Test
    public void iterableTest() {
        st.put("d", 2);
        st.put("b", 2);
        st.put("a", 1);
        st.put("c", 3);
        st.put("z", 3);
        st.put("k", 3);

        Iterator<String> iterator = st.keys().iterator();

        Assert.assertEquals("a", iterator.next());
        Assert.assertEquals("b", iterator.next());
        Assert.assertEquals("c", iterator.next());
        Assert.assertEquals("d", iterator.next());
        Assert.assertEquals("k", iterator.next());
        Assert.assertEquals("z", iterator.next());

        Assert.assertFalse(iterator.hasNext());
    }

    @Test
    public void deleteRootNodeTest() {
        st.put("root", 7);

        Assert.assertEquals(1, st.size());

        st.delete("root");

        Assert.assertEquals(0, st.size());
        Assert.assertNull(st.get("root"));
    }

    /**
     * Generate ST with BST to test deletion
     *            7
     *      2            21
     *   1     5      15    38
     *       3   6        36   55
     *                   37   54
     *
     * 14 nodes
     *
     * @return st
     */
    private static ST<Integer, Integer> initBST() {
        ST<Integer, Integer> st = new STRedBlackBST<>();

        st.put(7, 7);
        st.put(2, 2);
        st.put(1, 1);
        st.put(5, 5);
        st.put(3, 3);
        st.put(6, 6);
        st.put(4, 4);
        st.put(21, 21);
        st.put(15, 15);
        st.put(38, 38);
        st.put(36, 36);
        st.put(55, 55);
        st.put(54, 54);
        st.put(37, 37);

        return st;
    }

    @Test
    public void deleteNonExistingKey() {
        ST<Integer, Integer> st = initBST();
        int size = st.size();

        Assert.assertEquals(null, st.get(777));

        st.delete(777);

        Assert.assertEquals(null, st.get(777));
        Assert.assertEquals(size, st.size());
    }

    @Test
    public void deleteKeyWithoutChildren() {
        ST<Integer, Integer> st = initBST();

        Assert.assertEquals(14, st.size());
        Assert.assertEquals(37, (int)st.get(37));

        st.delete(37);
        Assert.assertEquals(null, st.get(37));
        Assert.assertEquals(13, st.size());
    }

    @Test
    public void deleteKeyWithOnlyLeftChildTest() {
        ST<Integer, Integer> st = initBST();

        Assert.assertEquals(14, st.size());
        Assert.assertEquals(55, (int)st.get(55));

        st.delete(55);

        Assert.assertEquals(null, st.get(55));
        Assert.assertEquals(13, st.size());
        Assert.assertEquals(54, (int)st.get(54));
    }

    @Test
    public void deleteKeyWithOnlyRightChildTest() {
        ST<Integer, Integer> st = initBST();

        Assert.assertEquals(14, st.size());
        Assert.assertEquals(36, (int)st.get(36));

        st.delete(36);

        Assert.assertEquals(null, st.get(36));
        Assert.assertEquals(13, st.size());
        Assert.assertEquals(37, (int)st.get(37));
    }

    @Test
    public void deleteKeyWithBothChildTest() {
        ST<Integer, Integer> st = initBST();

        Assert.assertEquals(14, st.size());

        Assert.assertEquals(5, (int)st.get(5));
        Assert.assertEquals(21, (int)st.get(21));

        st.delete(5);

        Assert.assertEquals(null, st.get(5));
        Assert.assertEquals(13, st.size());

        st.delete(21);

        Assert.assertEquals(null, st.get(21));
        Assert.assertEquals(12, st.size());

        Assert.assertEquals(3, (int)st.get(3));
        Assert.assertEquals(6, (int)st.get(6));

        Assert.assertEquals(15, (int)st.get(15));
        Assert.assertEquals(38, (int)st.get(38));

        Assert.assertEquals(37, (int)st.get(37));
        Assert.assertEquals(54, (int)st.get(54));
    }
}


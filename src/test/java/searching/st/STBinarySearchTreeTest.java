package searching.st;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

public class STBinarySearchTreeTest {
    private STBinarySearchTree<String, Integer> st;

    @Before
    public void beforeEach() {
        st = new STBinarySearchTree<>();
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

        Assert.assertEquals(false, iterator.hasNext());
    }
}


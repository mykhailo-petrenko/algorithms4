package searching.st;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

public class STLinkedListTest {
    private STLinkedList<String, Integer> st;

    @Before
    public void beforeEach() {
        st = new STLinkedList<>();
    }

    @Test()
    public void shouldPut() {
        st.put("a", 1);
    }

    @Test
    public void shouldIncreaseSizeAfterPut() {
        Assert.assertEquals(0, st.size());

        st.put("b", 1);

        Assert.assertEquals(1, st.size());

        st.put("c", 2);
        st.put("hello", 10);

        Assert.assertEquals(3, st.size());
    }

    @Test
    public void iterator() {
        st.put("a", 1);
        st.put("b", 1);
        st.put("c", 1);

        Iterator<String> stIterator = st.keys().iterator();

        Assert.assertEquals("c", stIterator.next());
        Assert.assertEquals("b", stIterator.next());
        Assert.assertEquals("a", stIterator.next());

        Assert.assertFalse(stIterator.hasNext());
    }

    @Test
    public void deleteShouldDecreaseSize() {
        st.put("a", 1);
        st.put("b", 1);
        st.put("c", 1);

        Assert.assertEquals(3, st.size());

        st.delete("a");
        st.delete("b");

        Assert.assertEquals(1, st.size());
    }

    @Test
    public void elementShouldBeBullAfterDeletion() {
        st.put("a", 2);
        st.put("b", 3);

        Assert.assertEquals(2, (int)st.get("a"));
        Assert.assertEquals(3, (int)st.get("b"));

        st.delete("a");
        st.delete("b");

        Assert.assertNull(st.get("a"));
        Assert.assertNull(st.get("b"));
    }
}

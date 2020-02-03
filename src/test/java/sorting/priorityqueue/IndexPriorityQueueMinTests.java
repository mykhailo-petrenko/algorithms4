package sorting.priorityqueue;

import org.junit.Assert;
import org.junit.Test;

public class IndexPriorityQueueMinTests {
    @Test
    public void basicInsertion() {
        IndexPriorityQueueMin<String> q = new IndexPriorityQueueMin<>(10);

        q.insert(1, "B");
        q.insert(3, "C");
        q.insert(2, "A");
        q.insert(5, "T");

        System.out.println(q);

        Assert.assertEquals("A", q.peekKey());
        Assert.assertEquals(2, q.peek());
        Assert.assertFalse(q.isEmpty());

    }

    @Test
    public void dequeueTest() {
        IndexPriorityQueueMin<String> q = new IndexPriorityQueueMin<>(10);

        q.insert(1, "B");
        q.insert(3, "C");
        q.insert(2, "A");
        q.insert(5, "T");

        System.out.println(q);

        Assert.assertEquals(4, q.size());
        Assert.assertEquals("A", q.peekKey());
        Assert.assertEquals(2, q.dequeue());

        System.out.println(q);

        Assert.assertEquals(3, q.size());
        Assert.assertEquals("B", q.peekKey());
        Assert.assertEquals(1, q.dequeue());

        System.out.println(q);

        Assert.assertEquals(2, q.size());
        Assert.assertEquals("C", q.peekKey());
        Assert.assertEquals(3, q.dequeue());

        System.out.println(q);

        Assert.assertEquals(1, q.size());
        Assert.assertEquals("T", q.peekKey());
        Assert.assertEquals(5, q.dequeue());

        Assert.assertEquals(0, q.size());
        Assert.assertTrue(q.isEmpty());
    }

    @Test
    public void deleteTest() {
        IndexPriorityQueueMin<String> q = new IndexPriorityQueueMin<>(10);

        q.insert(1, "B");
        q.insert(3, "C");
        q.insert(2, "A");
        q.insert(5, "T");

        Assert.assertEquals(4, q.size());

        System.out.println(q);

        q.delete(3);

        System.out.println(q);

        Assert.assertEquals(3, q.size());
        Assert.assertEquals("A", q.peekKey());
        Assert.assertEquals(2, q.dequeue());

        Assert.assertEquals(2, q.size());
        Assert.assertEquals("B", q.peekKey());
        Assert.assertEquals(1, q.dequeue());

        Assert.assertEquals(1, q.size());
        Assert.assertEquals("T", q.peekKey());
        Assert.assertEquals(5, q.dequeue());

        Assert.assertEquals(0, q.size());
        Assert.assertTrue(q.isEmpty());
    }
}

package data.structures.list.fifo;

import org.junit.Assert;
import org.junit.Test;

public class LinkedListQueueTest {

    @Test()
    public void firstInFirstOut() {
        LinkedListQueue<String> queue = new LinkedListQueue<>();

        queue.enqueue("Bob");
        queue.enqueue("Alisa");
        queue.enqueue("John");
        queue.enqueue("Dou");

        Assert.assertEquals(queue.dequeue(), "Bob");
        Assert.assertEquals(queue.dequeue(), "Alisa");
        Assert.assertEquals(queue.dequeue(), "John");
        Assert.assertEquals(queue.dequeue(), "Dou");
    }

    @Test()
    public void shouldBeIterable() {
        LinkedListQueue<String> queue = new LinkedListQueue<>();

        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");
        queue.enqueue("d");
        queue.enqueue("e");

        StringBuilder builder = new StringBuilder();

        for (String item : queue) {
            builder.append(item);
        }

        String actual = builder.toString();

        Assert.assertEquals(actual, "abcde");
    }

    @Test()
    public void shouldIncreaseSize() {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();

        Assert.assertEquals(queue.size(), 0);

        queue.enqueue(1);
        Assert.assertEquals(queue.size(), 1);

        queue.enqueue(2);
        Assert.assertEquals(queue.size(), 2);

        queue.enqueue(3);
        Assert.assertEquals(queue.size(), 3);

        queue.enqueue(4);
        Assert.assertEquals(queue.size(), 4);
    }

    @Test()
    public void shouldDecreaseSizeOnDequeue() {
        LinkedListQueue<String> queue = new LinkedListQueue<>();

        queue.enqueue("uno");
        queue.enqueue("dos");
        queue.enqueue("tres");
        Assert.assertEquals(queue.size(), 3);

        queue.dequeue();
        Assert.assertEquals(queue.size(), 2);

        queue.dequeue();
        Assert.assertEquals(queue.size(), 1);
    }
}

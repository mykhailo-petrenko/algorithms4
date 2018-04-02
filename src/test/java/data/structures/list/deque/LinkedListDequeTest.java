package data.structures.list.deque;

import org.junit.Assert;
import org.junit.Test;

public class LinkedListDequeTest {

    @Test()
    public void pushRightTest() {
        LinkedListDeque<Number> d = new LinkedListDeque<>();

        d.pushRight(1);
        d.pushRight(2);
        d.pushRight(3);
        d.pushRight(4);
        d.pushRight(5);

        Assert.assertEquals(d.toString(), "LinkedListDeque{N=5}: 1, 2, 3, 4, 5");
    }

    @Test()
    public void pushLeftTest() {
        LinkedListDeque<Number> d = new LinkedListDeque<>();

        d.pushLeft(6);
        d.pushLeft(5);
        d.pushLeft(4);
        d.pushLeft(3);
        d.pushLeft(2);
        d.pushLeft(1);

        Assert.assertEquals(d.toString(), "LinkedListDeque{N=6}: 1, 2, 3, 4, 5, 6");
    }

    @Test()
    public void popLeftTest() {
        LinkedListDeque<Number> d = new LinkedListDeque<>();

        d.pushLeft(5);
        d.pushLeft(4);
        d.pushLeft(3);
        d.pushLeft(2);
        d.pushLeft(1);

        Assert.assertEquals(d.popLeft(), 1);
        Assert.assertEquals(d.popLeft(), 2);
        Assert.assertEquals(d.popLeft(), 3);
        Assert.assertEquals(d.popLeft(), 4);
        Assert.assertEquals(d.popLeft(), 5);

        Assert.assertEquals(d.size(), 0);
        Assert.assertTrue(d.isEmpty());
    }

    @Test()
    public void popRightTest() {
        LinkedListDeque<Number> d = new LinkedListDeque<>();

        d.pushRight(5);
        d.pushRight(4);
        d.pushRight(3);
        d.pushRight(2);
        d.pushRight(1);

        Assert.assertEquals(d.popRight(), 1);
        Assert.assertEquals(d.popRight(), 2);
        Assert.assertEquals(d.popRight(), 3);
        Assert.assertEquals(d.popRight(), 4);
        Assert.assertEquals(d.popRight(), 5);

        Assert.assertEquals(d.size(), 0);
        Assert.assertTrue(d.isEmpty());
    }
}

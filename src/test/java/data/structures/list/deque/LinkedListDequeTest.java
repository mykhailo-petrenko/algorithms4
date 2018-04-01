package data.structures.list.deque;

import org.junit.Test;

public class LinkedListDequeTest {

    @Test()
    public void pushRightTest() {
        LinkedListDeque<Number> d = new LinkedListDeque<>();

        d.pushRight(1);
        d.pushRight(2);
        d.pushRight(3);
        d.pushRight(4);

        System.out.println(d);
    }

    @Test()
    public void pushLeftTest() {
        LinkedListDeque<Number> d = new LinkedListDeque<>();

        d.pushLeft(4);
        d.pushLeft(3);
        d.pushLeft(2);
        d.pushLeft(1);

        System.out.println(d);
    }
}

package data.structures.list.lifo;

import org.junit.Assert;
import org.junit.Test;

public class FixedSizeStackTest {

    @Test()
    public void lastInFirstOut() {
        FixedSizeStack<String> s = new FixedSizeStack<>(4);

        s.push("uno");
        s.push("dos");
        s.push("tres");

        Assert.assertEquals(s.pop(), "tres");
        Assert.assertEquals(s.pop(), "dos");
        Assert.assertEquals(s.pop(), "uno");
    }

    @Test()
    public void shouldIncreaseOnOverflow() {
        FixedSizeStack<Integer> s = new FixedSizeStack<>(1);

        s.push(1);
        Assert.assertEquals(s.size(), 1);

        s.push(2);
        Assert.assertEquals(s.size(), 2);

        s.push(3);
        Assert.assertEquals(s.size(), 4);

        s.push(4);
        s.push(4);
        Assert.assertEquals(s.size(), 8);

        System.out.println(s);
    }

    @Test()
    public void shouldDecrease() {
        FixedSizeStack<Integer> s = new FixedSizeStack<>(5);

        Assert.assertEquals(s.size(), 5);

        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        s.push(5);

        while (!s.isEmpty()) {
            s.pop();
        }

        Assert.assertEquals(s.size(), 2);

        System.out.println(s);
    }

    @Test()
    public void iterator() {
        FixedSizeStack<Integer> s = new FixedSizeStack<>(4);

        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);

        int[] actual = new int[4];
        int[] expected = {4, 3, 2, 1};
        int i = 0;
        for(int number: s) {
            actual[i++] = number;
        }

        Assert.assertArrayEquals(actual, expected);
    }
}

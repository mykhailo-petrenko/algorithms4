import org.junit.Test;

public class FixedSizeStackTest {

    @Test()
    public void shouldIncreaseOnOverflow() {
        FixedSizeStack<Integer> s = new FixedSizeStack(1);

        s.push(1);
        System.out.println(s);
        s.push(2);
        System.out.println(s);
        s.push(3);
        System.out.println(s);
        s.push(4);
        System.out.println(s);
        s.push(4);
        System.out.println(s);
    }

    @Test()
    public void shouldDecrease() {
        FixedSizeStack<Integer> s = new FixedSizeStack(5);

        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        s.push(5);

        while (!s.isEmpty()) {
            System.out.println(s.pop());
            System.out.println(s);
        }
    }
}

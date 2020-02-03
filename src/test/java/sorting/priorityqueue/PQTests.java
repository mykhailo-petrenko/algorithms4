package sorting.priorityqueue;

import org.junit.Assert;
import org.junit.Test;

public class PQTests {
    @Test
    public void swimTest() {
        int[] list = new int[] {
            1, 3, 5, 7, 8, 4
        };

        PQ<Integer> q = new PriorityQueueMax<>();

        for (int k: list) {
            q.insert(k);
        }

        int actual = q.peek();

        Assert.assertEquals(8, actual);

//        System.out.println(q.toString());
    }

    @Test
    public void tinyTest() {
        double[] in = new double[]{
            644.08, 4121.85, 2678.40, 4409.74, 837.42,
            3229.27, 4732.35, 4381.21, 66.10, 4747.08,
            2156.86, 1025.70, 2520.97, 708.95, 3532.36,
            4050.20
        };

        PQ<Double> q = new PriorityQueueMax<>();

        for (double v: in) {
            q.insert(v);
        }

        double[] expected = new double[]{
            4747.08, 4732.35, 4409.74, 4381.21, 4121.85
        };

        Assert.assertEquals(16, q.size());

        for (double expectedValue : expected) {
//            System.out.println(q.toString());
            double value = q.pop();
            Assert.assertEquals(expectedValue, value, 0.01);
        }

    }
}

package training;

import org.junit.Assert;
import org.junit.Test;

public class StockSpannerTests {
    public static void testStockRunner(int[] input, int[] expected ){
        StockSpanner spanner = new StockSpanner();

        for (int i = 0; i < input.length; i++) {
            Assert.assertEquals(
                expected[i],
                spanner.next(input[i])
            );
        }
    }

    @Test
    public void smoke1() {
        testStockRunner(
            new int[] {100, 80, 60, 70, 60, 75, 85},
            new int[] {1, 1, 1, 2, 1, 4, 6}
        );
    }

    @Test
    public void smoke2() {
        testStockRunner(
            new int[] {29, 91, 62, 76, 51},
            new int[] {1, 2, 1, 2, 1}
        );
    }

    @Test
    public void smoke3() {
        testStockRunner(
            new int[] {29, 91, 62, 76, 51, 60, 60, 60, 72, 82},
            new int[] {1, 2, 1, 2, 1, 2, 3, 4, 5, 8}
        );
    }
}

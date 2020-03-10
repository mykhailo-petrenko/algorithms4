package training.arrays;

import org.junit.Assert;
import org.junit.Test;

public class BestTimeToBuyTests {
    @Test
    public void example1() {
        Assert.assertEquals(
                7,
                new BestTimeToBuy().maxProfit(new int[]{7, 1, 5, 3, 6, 4})
        );
    }

    @Test
    public void example2() {
        Assert.assertEquals(
                4,
                new BestTimeToBuy().maxProfit(new int[]{1, 2, 3, 4, 5})
        );
    }

    @Test
    public void example3() {
        Assert.assertEquals(
                0,
                new BestTimeToBuy().maxProfit(new int[]{7, 6, 4, 3, 1})
        );
    }
}

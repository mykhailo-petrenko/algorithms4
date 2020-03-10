package training.arrays;

import edu.princeton.cs.introcs.In;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BestTimeToBuyTests {
    BestTimeToBuy bestTimeToBuy;

    @Before
    public void beforeEach() {
//        bestTimeToBuy = new BestTimeToBuyBruteForce();
        bestTimeToBuy = new BestTimeToBuyQuick();
    }

    @Test
    public void example1() {
        Assert.assertEquals(
                7,
                bestTimeToBuy.maxProfit(new int[]{7, 1, 5, 3, 6, 4})
        );
    }

    @Test
    public void example2() {
        Assert.assertEquals(
                4,
                bestTimeToBuy.maxProfit(new int[]{1, 2, 3, 4, 5})
        );
    }

    @Test
    public void example3() {
        Assert.assertEquals(
                0,
                bestTimeToBuy.maxProfit(new int[]{7, 6, 4, 3, 1})
        );
    }

    @Test
    public void zeroElement1() {
        Assert.assertEquals(
            7,
            bestTimeToBuy.maxProfit(new int[]{3, 2, 6, 5, 0, 3})
        );
    }

    @Test
    public void zeroElement2() {
        Assert.assertEquals(
            15,
            bestTimeToBuy.maxProfit(new int[]{1, 2, 4, 2, 5, 7, 2, 4, 9, 0})
        );
    }

    @Test
    public void empty() {
        Assert.assertEquals(
            0,
            bestTimeToBuy.maxProfit(new int[]{})
        );
    }

    @Test
    public void minimal() {
        Assert.assertEquals(
            0,
            bestTimeToBuy.maxProfit(new int[]{100})
        );
    }

    @Test
    public void performance() {
        int[] prices = new In("data/my/best-time-to-buy.medium.txt").readAllInts();
        Assert.assertEquals(1697678, bestTimeToBuy.maxProfit(prices));
    }

    @Test
    public void decreases() {
        int[] prices = new In("data/my/best-time-to-buy.large.txt").readAllInts();
        Assert.assertEquals(4, bestTimeToBuy.maxProfit(prices));
    }
}

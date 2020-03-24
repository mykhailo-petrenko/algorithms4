package training.uf;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RemoveMarblesTests {
    public RemoveMarbles rm;

    @Before
    public void beforeEach() {
        rm = new RemoveMarbles();
    }

    @Test
    public void test1() {
        int[][] stones = new int[][] {{0, 0}, {0, 1}, {1, 0}, {1, 2}, {2, 1}, {2, 2}};

        Assert.assertEquals(5, rm.removeStones(stones));
    }

    @Test
    public void test2() {
        int[][] stones = new int[][] {{0, 0}, {0, 2}, {1, 1}, {2, 0}, {2, 2}};

        Assert.assertEquals(3, rm.removeStones(stones));
    }

    @Test
    public void test3() {
        int[][] stones = new int[][] {{0, 0}};

        Assert.assertEquals(0, rm.removeStones(stones));
    }

    @Test
    public void interviewTest1() {
        int[][] stones = new int[][] {
            {0, 0},
            {0, 1},
            {0, 2},
            {1, 0},
        };

        Assert.assertEquals(
            3,
            rm.removeStones(stones)
        );
    }

    @Test
    public void interviewTest2() {
        int[][] stones = new int[][] {
            {0, 2},
            {0, 3},
            {1, 2},
            {2, 1},
            {3, 1},
        };

        Assert.assertEquals(
            3,
            rm.removeStones(stones)
        );
    }

    @Test
    public void interviewTest12Extended() {
        int[][] stones = new int[][] {
            {0, 2},
            {0, 3},
            {1, 2},
            {2, 2},
            {2, 1},
            {3, 1},
        };

        Assert.assertEquals(
            5,
            rm.removeStones(stones)
        );
    }
}

package withgoogle.foobar.running;

import org.junit.Assert;
import org.junit.Test;

public class SolutionTests {
    @Test
    public void openTest1() {
        Assert.assertArrayEquals(
            new int[]{0, 1},
            Solution.solution(
                new int [][]{{0, 1, 1, 1, 1}, {1, 0, 1, 1, 1}, {1, 1, 0, 1, 1}, {1, 1, 1, 0, 1}, {1, 1, 1, 1, 0}},
                3
            )
        );
    }


    @Test
    public void openTest2() {
        Assert.assertArrayEquals(
            new int[]{1, 2},
            Solution.solution(
                new int[][]{{0, 2, 2, 2, -1}, {9, 0, 2, 2, -1}, {9, 3, 0, 2, -1}, {9, 3, 2, 0, -1}, {9, 3, 2, 2, 0}},
                1
            )
        );
    }
}

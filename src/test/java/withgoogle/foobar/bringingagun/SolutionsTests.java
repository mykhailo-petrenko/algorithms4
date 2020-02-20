package withgoogle.foobar.bringingagun;

import org.junit.Assert;
import org.junit.Test;

public class SolutionsTests {
    @Test
    public void open1() {
        Assert.assertEquals(
            7,
            Solution.solution(new int[]{3,2}, new int[]{1,1}, new int[]{2,1}, 4)
        );
    }

    @Test
    public void open2() {
        Assert.assertEquals(
            9,
            Solution.solution(new int[] {300, 275}, new int[] {150, 150}, new int[] {185, 100}, 500)
        );
    }

    @Test
    public void diagonalEnemy() {
        Assert.assertEquals(
            13,
            Solution.solution(new int[] {4, 4}, new int[] {1, 1}, new int[] {3, 3}, 10)
        );
    }

    @Test
    public void perf() {
        int result = Solution.solution(new int[] {4, 4}, new int[] {1, 1}, new int[] {3, 3}, 1000);
        System.out.println(result);
    }
}

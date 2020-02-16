package withgoogle.foobar.bringingagun;

import org.junit.Assert;
import org.junit.Test;

public class SolutionsTests {
    @Test
    public void open1() {
        Assert.assertEquals(
            Solution.solution(new int[]{3,2}, new int[]{1,1}, new int[]{2,1}, 4),
            7
        );
    }

    @Test
    public void open2() {
        Assert.assertEquals(
            Solution.solution(new int[] {300, 275}, new int[] {150, 150}, new int[] {185, 100}, 500),
            9
        );
    }
}

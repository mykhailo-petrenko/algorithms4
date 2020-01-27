package withgoogle.foobar.queuetodo;

import org.junit.Assert;
import org.junit.Test;

public class SolutionTest {

    @Test
    public void positionThree() {
        Assert.assertEquals(
            2,
            Solution.solution(0, 3)
        );
    }

    @Test
    public void positionSeventeen() {
        Assert.assertEquals(
            14,
            Solution.solution(17, 4)
        );
    }
}

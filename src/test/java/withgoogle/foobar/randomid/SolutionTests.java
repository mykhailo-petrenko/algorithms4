package withgoogle.foobar.randomid;

import org.junit.Assert;
import org.junit.Test;

public class SolutionTests {
    @Test
    public void baseTest() {
        Assert.assertEquals(3, Solution.solution("210022", 3));
    }

    @Test
    public void baseOneTest() {
        Assert.assertEquals(1, Solution.solution("1211", 10));
    }
}

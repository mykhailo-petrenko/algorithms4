package withgoogle.foobar.fuelinjection;

import org.junit.Assert;
import org.junit.Test;

public class SolutionTest {
    @Test
    public void maximum() {
        int answer = Solution.solution("179769313486231590772930519078902473361797697894230657273430081157732675805500963132708477322407536021120113879871393357658789768814416622492847430639474124377767893424865485276302219601246094119453082952085005768838150682342462881473913110540827237163350510684586298239947245938479716304835356329624224137215");

        System.out.println(answer);
        Assert.assertEquals(1025, answer);

        int answer1024 = Solution.solution("179769313486231590772930519078902473361797697894230657273430081157732675805500963132708477322407536021120113879871393357658789768814416622492847430639474124377767893424865485276302219601246094119453082952085005768838150682342462881473913110540827237163350510684586298239947245938479716304835356329624224137216");

        System.out.println(answer1024);
        Assert.assertEquals(1024, answer1024);
    }

    @Test
    public void bbb() {
        Assert.assertEquals(5, Solution.solution("11"));
    }
    @Test
    public void testSmallVal() {
        Assert.assertEquals(0, Solution.solution("1"));
        Assert.assertEquals(1, Solution.solution("2"));
        Assert.assertEquals(2, Solution.solution("3"));
        Assert.assertEquals(2, Solution.solution("4"));
        Assert.assertEquals(3, Solution.solution("5"));
        Assert.assertEquals(3, Solution.solution("6"));
    }

    @Test
    public void testBasic() {
        Assert.assertEquals(4, Solution.solution("7"));
        Assert.assertEquals(3, Solution.solution("8"));
        Assert.assertEquals(4, Solution.solution("9"));
        Assert.assertEquals(4, Solution.solution("10"));
        Assert.assertEquals(5, Solution.solution("11"));
        Assert.assertEquals(4, Solution.solution("12"));
        Assert.assertEquals(5, Solution.solution("13"));
        Assert.assertEquals(5, Solution.solution("14"));
    }

    @Test
    public void testThree() {
        Assert.assertEquals(5, Solution.solution("15"));
        Assert.assertEquals(4, Solution.solution("16"));
        Assert.assertEquals(5, Solution.solution("17"));
    }

    @Test
    public void testNear32() {
        Assert.assertEquals(6, Solution.solution("30"));
        Assert.assertEquals(6, Solution.solution("31"));
        Assert.assertEquals(5, Solution.solution("32"));
    }
}

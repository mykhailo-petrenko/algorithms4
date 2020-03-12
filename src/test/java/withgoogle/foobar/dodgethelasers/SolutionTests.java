package withgoogle.foobar.dodgethelasers;

import org.junit.Assert;
import org.junit.Test;

public class SolutionTests {
    @Test
    public void openTest1() {
        Assert.assertEquals("4208", Solution.solution("77"));
    }

    @Test
    public void openTest2() {
        Assert.assertEquals("19", Solution.solution("5"));
    }

    @Test
    public void test30() {
        Assert.assertEquals("1", Solution.solution("1"));
        Assert.assertEquals("3", Solution.solution("2"));
        Assert.assertEquals("7", Solution.solution("3"));
        Assert.assertEquals("12", Solution.solution("4"));
        Assert.assertEquals("19", Solution.solution("5"));
        Assert.assertEquals("27", Solution.solution("6"));
        Assert.assertEquals("36", Solution.solution("7"));
        Assert.assertEquals("47", Solution.solution("8"));
        Assert.assertEquals("59", Solution.solution("9"));
        Assert.assertEquals("73", Solution.solution("10"));
        Assert.assertEquals("88", Solution.solution("11"));
        Assert.assertEquals("104", Solution.solution("12"));
        Assert.assertEquals("122", Solution.solution("13"));
        Assert.assertEquals("141", Solution.solution("14"));
        Assert.assertEquals("162", Solution.solution("15"));
        Assert.assertEquals("184", Solution.solution("16"));
        Assert.assertEquals("208", Solution.solution("17"));
        Assert.assertEquals("233", Solution.solution("18"));
        Assert.assertEquals("259", Solution.solution("19"));
        Assert.assertEquals("287", Solution.solution("20"));
        Assert.assertEquals("316", Solution.solution("21"));
        Assert.assertEquals("347", Solution.solution("22"));
        Assert.assertEquals("379", Solution.solution("23"));
        Assert.assertEquals("412", Solution.solution("24"));
        Assert.assertEquals("447", Solution.solution("25"));
        Assert.assertEquals("483", Solution.solution("26"));
        Assert.assertEquals("521", Solution.solution("27"));
        Assert.assertEquals("560", Solution.solution("28"));
        Assert.assertEquals("601", Solution.solution("29"));
        Assert.assertEquals("643", Solution.solution("30"));
    }

    @Test
    public void test100() {
        Assert.assertEquals("7092", Solution.solution("100"));
    }

    @Test
    public void test200() {
        Assert.assertEquals("28326", Solution.solution("200"));
    }

    @Test
    public void test1000() {
        Assert.assertEquals("707314", Solution.solution("1000"));
    }

    @Test
    public void testN() {
        Assert.assertEquals("825", Solution.solution("34"));
//        Assert.assertEquals("3887", Solution.solution("74"));
    }

    @Test
    public void generatorTest() {
        int N = 1000;
        int sum = 0;
        double sqrt2 = Math.sqrt(2);

        for (int i = 1; i <= N; i++) {
            sum += Math.floor( i * sqrt2 );
//            System.out.printf("%d\t%d\n", i, sum);
//            System.out.printf("%d ", sum);
            System.out.printf("%d %d\n", i, sum);

            Assert.assertEquals(
                Integer.toString(sum),
                Solution.solution(Integer.toString(i))
            );
        }
    }
}

package training.slidingwindow;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LongestNiceSubstringTest {
    LongestNiceSubstring nice;

    @Before()
    public void before() {
        nice = new LongestNiceSubstring();
    }


    @Test()
    public void Example1() {
        String actual = nice.longestNiceSubstring("YazaAay");
        Assert.assertEquals("aAa", actual);
    }

    @Test()
    public void Example2() {
        String actual = nice.longestNiceSubstring("Bb");
        Assert.assertEquals("Bb", actual);
    }

    @Test()
    public void Example3() {
        String actual = nice.longestNiceSubstring("c");
        Assert.assertEquals( "", actual);
    }

    @Test()
    public void Example4() {
        String actual = nice.longestNiceSubstring("dDzeE");
        Assert.assertEquals("dD", actual);
    }

    @Test()
    public void Example5() {
        String actual = nice.longestNiceSubstring("BsQSbWKRqwruU");
        Assert.assertEquals("uU", actual);
    }

}

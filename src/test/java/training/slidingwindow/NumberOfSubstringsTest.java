package training.slidingwindow;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NumberOfSubstringsTest {
    public NumberOfSubstrings substrings;

    @Before
    public void Before() {
        substrings = new NumberOfSubstrings();
    }

    @Test()
    public void Example1() {
        int actual = substrings.numberOfSubstrings("abcabc");

        Assert.assertEquals(10, actual);
    }

    @Test()
    public void Example2() {
        int actual = substrings.numberOfSubstrings("aaacb");

        Assert.assertEquals(3, actual);
    }

    @Test()
    public void Example3() {
        int actual = substrings.numberOfSubstrings("abc");

        Assert.assertEquals(1, actual);
    }

    @Test()
    public void Test1() {
        int actual = substrings.numberOfSubstrings("ababbbc");

        Assert.assertEquals(3, actual);
    }

    @Test()
    public void Test2() {
        int actual = substrings.numberOfSubstrings("acbbcac");

        Assert.assertEquals(11, actual);
    }
}

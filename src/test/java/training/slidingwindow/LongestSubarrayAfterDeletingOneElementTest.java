package training.slidingwindow;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LongestSubarrayAfterDeletingOneElementTest {

    public LongestSubarrayAfterDeletingOneElement longestSubarray;

    @Before()
    public void before() {
        longestSubarray = new LongestSubarrayAfterDeletingOneElement();
    }

    @Test()
    public void Example1() {
        int actual = longestSubarray.longestSubarray(new int[]{1,1,0,1});

        Assert.assertEquals(3, actual);
    }

    @Test()
    public void Example2() {
        int actual = longestSubarray.longestSubarray(new int[]{0,1,1,1,0,1,1,0,1});

        Assert.assertEquals(5, actual);
    }

    @Test()
    public void Example3() {
        int actual = longestSubarray.longestSubarray(new int[]{1,1,1});

        Assert.assertEquals(2, actual);
    }

    @Test()
    public void Example4() {
        int actual = longestSubarray.longestSubarray(new int[]{1,1,0,0,1,1,1,0,1});

        Assert.assertEquals(4, actual);
    }

    @Test()
    public void Example5() {
        int actual = longestSubarray.longestSubarray(new int[]{0,0,0});

        Assert.assertEquals(0, actual);
    }
}

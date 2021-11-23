package training.slidingwindow;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CountNumberOfNiceSubarraysTest {
    CountNumberOfNiceSubarrays count;

    @Before()
    public void before() {
        count = new CountNumberOfNiceSubarrays();
    }

    @Test()
    public void Example1() {
        int actual = count.numberOfSubarrays(new int[]{1,1,2,1,1}, 3);
        Assert.assertEquals(2, actual);
    }
    @Test()
    public void Example2() {
        int actual = count.numberOfSubarrays(new int[]{2,4,6}, 1);
        Assert.assertEquals(0, actual);
    }
    @Test()
    public void Example3() {
        int actual = count.numberOfSubarrays(new int[]{2,2,2,1,2,2,1,2,2,2}, 2);
        Assert.assertEquals(16, actual);
    }
}

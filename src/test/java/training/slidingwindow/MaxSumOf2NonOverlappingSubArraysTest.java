package training.slidingwindow;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MaxSumOf2NonOverlappingSubArraysTest {
    MaxSumOf2NonOverlappingSubArrays max;

    @Before()
    public void before() {
        max = new MaxSumOf2NonOverlappingSubArrays();
    }

    @Test()
    public void Example1() {
        int actual = max.maxSumTwoNoOverlap(new int[]{0,6,5,2,2,5,1,9,4}, 1, 2);
        Assert.assertEquals(20, actual);
    }

    @Test()
    public void Example2() {
        int actual = max.maxSumTwoNoOverlap(new int[]{3,8,1,3,2,1,8,9,0}, 3, 2);
        Assert.assertEquals(29, actual);
    }

    @Test()
    public void Example3() {
        int actual = max.maxSumTwoNoOverlap(new int[]{2,1,5,6,0,9,5,0,3,8}, 4, 3);
        Assert.assertEquals(31, actual);
    }

    @Test()
    public void MinLen() {
        int actual = max.maxSumTwoNoOverlap(new int[]{1,0,3}, 1, 2);
        Assert.assertEquals(4, actual);
    }

    @Test()
    public void OverLen() {
        int actual = max.maxSumTwoNoOverlap(new int[]{1,0,3}, 2, 2);
        Assert.assertEquals(0, actual);
    }
}

package training.slidingwindow;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MaxConsecutiveOnesIIITest {
    public MaxConsecutiveOnesIII maxOnes;

    @Before()
    public void before() {
        maxOnes = new MaxConsecutiveOnesIII();
    }

    /**
     * Example 1:
     *
     * Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
     * Output: 6
     * Explanation: [1,1,1,0,0,1,1,1,1,1,1]
     * Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
     */
    @Test()
    public void Example1() {
        int actual = maxOnes.longestOnes(new int[]{1,1,1,0,0,0,1,1,1,1,0}, 2);

        Assert.assertEquals(6, actual);
    }

     /**
     * Example 2:
     * Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
     * Output: 10
     * Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
     * Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
     */
     @Test()
     public void Example2() {
         int actual = maxOnes.longestOnes(new int[]{0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1}, 3);

         Assert.assertEquals(10, actual);
     }

    @Test()
    public void Zeroes() {
        int actual = maxOnes.longestOnes(new int[]{0,0,0,0,0,0,0,0}, 3);

        Assert.assertEquals(3, actual);
    }

    @Test()
    public void EmptyList() {
        int actual = maxOnes.longestOnes(new int[]{}, 3);

        Assert.assertEquals(0, actual);
    }
}

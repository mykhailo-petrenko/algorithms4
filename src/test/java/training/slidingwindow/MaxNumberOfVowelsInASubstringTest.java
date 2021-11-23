package training.slidingwindow;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MaxNumberOfVowelsInASubstringTest {
    MaxNumberOfVowelsInASubstring max;

    @Before()
    public void before() {
        max = new MaxNumberOfVowelsInASubstring();
    }

    @Test()
    public void Example1() {
        int actual = max.maxVowels("abciiidef", 3);
        Assert.assertEquals(3, actual);
    }

    @Test()
    public void Example2() {
        int actual = max.maxVowels("aeiou", 2);
        Assert.assertEquals(2, actual);
    }

    @Test()
    public void Example3() {
        int actual = max.maxVowels("leetcode", 3);
        Assert.assertEquals(2, actual);
    }

    @Test()
    public void Example4() {
        int actual = max.maxVowels("rhythms", 4);
        Assert.assertEquals(0, actual);
    }

    @Test()
    public void Example5() {
        int actual = max.maxVowels("tryhard", 4);
        Assert.assertEquals(1, actual);
    }

    @Test()
    public void Example6() {
        int actual = max.maxVowels("weallloveyou", 7);
        Assert.assertEquals(4, actual);
    }

}

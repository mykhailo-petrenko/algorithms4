package training.slidingwindow;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MaximiseTheConfusionOnExamTest {
    MaximiseTheConfusionOnExam instance;

    @Before()
    public void before() {
        instance = new MaximiseTheConfusionOnExam();
    }

    @Test()
    public void Example1 () {
        int actual = instance.maxConsecutiveAnswers("TTFF", 2);
        Assert.assertEquals(4, actual);
    }
    @Test()
    public void Example2 () {
        int actual = instance.maxConsecutiveAnswers("TFFT", 1);
        Assert.assertEquals(3, actual);
    }
    @Test()
    public void Example3 () {
        int actual = instance.maxConsecutiveAnswers("TTFTTFTT", 1);
        Assert.assertEquals(5, actual);
    }
    @Test()
    public void Case1 () {
        int actual = instance.maxConsecutiveAnswers("FTFFTFTFTTFTTFTTFFTTFFTTTTTFTTTFTFFTTFFFFFTTTTFTTTTTTTTTFTTFFTTFTFFTTTFFFFFTTTFFTTTTFTFTFFTTFTTTTTTF", 32);
        Assert.assertEquals(85, actual);
    }
}

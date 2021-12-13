package training.slidingwindow;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GrumpyBookstoreOwnerTest {
    GrumpyBookstoreOwner grumpy;

    @Before()
    public void before() {
        grumpy = new GrumpyBookstoreOwner();
    }

    @Test()
    public void Example1() {
        int actual = grumpy.maxSatisfied(new int[]{1,0,1,2,1,1,7,5}, new int[]{0,1,0,1,0,1,0,1}, 3);
        Assert.assertEquals(16, actual);
    }

    @Test()
    public void Example2() {
        int actual = grumpy.maxSatisfied(new int[]{1}, new int[]{0}, 1);
        Assert.assertEquals(1, actual);
    }

}

package training;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CourseSchedulerTests {
    public CourseScheduler o;

    @Before
    public void beforeEach() {
        o = new CourseScheduler();
    }

    @Test
    public void empty() {
        Assert.assertTrue(o.canFinish(
            2000,
            new int[][]{}
        ));
    }

    @Test
    public void smoke1() {
        Assert.assertTrue(o.canFinish(
            2,
            new int[][]{
                {0,1}
            }
        ));
    }

    @Test
    public void smoke2() {
        Assert.assertFalse(o.canFinish(
            2,
            new int[][]{
                {0,1},
                {1,0}
            }
        ));
    }

    @Test
    public void smoke6True() {
        Assert.assertTrue(o.canFinish(
            6,
            new int[][]{
                {0,2},
                {1,2},
                {3,1},
                {4,0},
                {5,2},
            }
        ));
    }

    @Test
    public void smoke6False() {
        Assert.assertFalse(o.canFinish(
            6,
            new int[][]{
                {0,2},
                {1,2},
                {3,1},
                {4,0},
                {2,5},
                {5,3},
            }
        ));
    }
}

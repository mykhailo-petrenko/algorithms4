package kata.bunniesescape;

import org.junit.Assert;
import org.junit.Test;

public class SolutionTests {

    @Test
    public void impassableWallAtStart() {
        Assert.assertEquals(
            7,
            Solution.solution(new int[][]{
                {0, 0, 1, 0},
                {0, 0, 1, 1},
                {1, 1, 0, 0},
                {1, 1, 0, 0}
            })
        );
    }

    @Test
    public void impassableWallInMid() {
        Assert.assertEquals(
            9,
            Solution.solution(new int[][]{
                {0, 1, 1, 0, 1},
                {0, 1, 1, 0, 1},
                {1, 1, 0, 0, 1},
                {1, 1, 0, 0, 1},
                {1, 1, 0, 0, 0}
            })
        );
    }

    @Test
    public void wallTest() {
        Assert.assertEquals(
            7,
            Solution.solution(new int[][]{
                {0, 0, 1, 0},
                {0, 0, 1, 1},
                {1, 0, 0, 0},
                {1, 1, 0, 0}
            })
        );
    }

    @Test
    public void openTest1() {
        Assert.assertEquals(
            7,
            Solution.solution(new int[][]{
                {0, 1, 1, 0},
                {0, 0, 0, 1},
                {1, 1, 0, 0},
                {1, 1, 1, 0}
            })
        );
    }

    @Test
    public void openTest2() {
        Assert.assertEquals(
            11,
            Solution.solution(new int[][]{
                {0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1},
                {0, 1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0, 0}
            })
        );
    }

    @Test
    public void openTestVS() {
        Assert.assertEquals(
            8,
            Solution.solution(new int[][]{
                {0, 0, 0, 0},
                {1, 1, 1, 0},
                {0, 0, 0, 0},
                {0, 1, 1, 1},
                {0, 0, 0, 0}
            })
        );
    }

    @Test
    public void openTestVL() {
        Assert.assertEquals(
            12,
            Solution.solution(new int[][]{
                {0, 0, 0, 0, 0, 0},
                {0, 1, 0, 1, 1, 0},
                {0, 0, 0, 1, 0, 0},
                {1, 1, 1, 0, 0, 1},
                {0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0, 0}
            })
        );
    }
}

package withgoogle.foobar.running;

import org.junit.Assert;
import org.junit.Test;

public class SolutionTests {
    public static final int[][] g1Easy = new int [][]{
        {0, 1, 1, 1, 1},
        {1, 0, 1, 1, 1},
        {1, 1, 0, 1, 1},
        {1, 1, 1, 0, 1},
        {1, 1, 1, 1, 0}
    };

    @Test
    public void openTest1() {
        int[] bunnies = Solution.solution(g1Easy, 3);
        Assert.assertArrayEquals(new int[]{0, 1}, bunnies);
    }

    public static final int[][] g2Negatives = new int[][]{
        {0, 2, 2, 2, -1},
        {9, 0, 2, 2, -1},
        {9, 3, 0, 2, -1},
        {9, 3, 2, 0, -1},
        {9, 3, 2, 2, 0}
    };

    @Test
    public void openTest2() {
        int[] bunnies = Solution.solution(g2Negatives, 1);

        Assert.assertArrayEquals(new int[]{1, 2}, bunnies);
    }

    @Test
    public void maxTimeLimit() {
        int[] bunnies = Solution.solution(
            new int[][]{
                {0, 2, 2, 2, -1},
                {9, 0, 2, 2, -1},
                {9, 3, 0, 2, -1},
                {900, 900, 900, 0, 900},
                {9, 3, 2, 2, 0}
            },
            999
        );

        Assert.assertArrayEquals(new int[]{0, 1, 2}, bunnies);
    }

    public static final int[][] g3Loops = new int[][]{
        {0,  1,  10, 10, 10, 10, 10},
        {-2, 0,  10, 10, 10, 10, 10},
        {10, 10, 0,  10, 10, 10, 10},
        {10, 10, 10, 0,  10, 10, 10},
        {10, 10, 10, 10, 0,  10, 10},
        {10, 10, 10, 10, 10, 0,  10},
        {10, 10, 10, 10, 10, 10, 0 },
    };

    @Test
    public void testN() {
        int[] bunnies = Solution.solution(g3Loops, 1);

        Assert.assertArrayEquals(new int[]{0, 1, 2, 3, 4}, bunnies);
    }

    @Test
    public void loopsDetector() {
        Solution.Digraph g1 = new Solution.Digraph(g1Easy);
        Solution.Digraph g2 = new Solution.Digraph(g2Negatives);
        Solution.Digraph g3 = new Solution.Digraph(g3Loops);

        Solution.LoopsDetector finder3 = new Solution.LoopsDetector(g3);

        Assert.assertTrue(finder3.hasNegativeLoops());

        Solution.LoopsDetector finder1 = new Solution.LoopsDetector(g1);
        Assert.assertFalse(finder1.hasNegativeLoops());

        Solution.LoopsDetector finder2 = new Solution.LoopsDetector(g2);
        Assert.assertFalse(finder2.hasNegativeLoops());

    }

    public static final int[][] g4zero = new int[][]{
        {0,  0,  0, 0, 0, 0, 0},
        {0,  0,  0, 0, 0, 0, 0},
        {0,  0,  0, 0, 0, 0, 0},
        {0,  0,  0, 0, 0, 0, 0},
        {0,  0,  0, 0, 0, 0, 0},
        {0,  0,  0, 0, 0, 0, 0},
        {0,  0,  0, 0, 0, 0, 0},
    };

    @Test
    public void zeroPathsTest() {
        int[] bunnies = Solution.solution(g4zero, 1);
        Assert.assertArrayEquals(new int[]{0, 1, 2, 3, 4}, bunnies);
    }
}

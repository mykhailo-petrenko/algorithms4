package withgoogle.foobar.running;

import org.junit.Assert;
import org.junit.Test;

public class SolutionTests {


    public static void main(String[] args) {
        print( Solution.allShortestPath(g2Negatives) );

        print( Solution.allShortestPath(g5moreMoves) );
    }

    public static void print(int[][] matrix) {
        System.out.println("");

        for (int i = 0; i < matrix.length; i++) {
            System.out.print("[");

            for (int j = 0; j < matrix.length; j++) {
                System.out.print("\t" + matrix[i][j]);
            }

            System.out.println("\t]");
        }
    }

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
        print(g2Negatives);
        print( Solution.allShortestPath(g2Negatives) );

        int[] bunnies = Solution.solution(g2Negatives, 1);

        Assert.assertArrayEquals(new int[]{1, 2}, bunnies);
    }

    public static final int[][] gMax = new int[][]{
        {0, 2, 2, 2, -1},
        {9, 0, 2, 2, -1},
        {9, 3, 0, 2, -1},
        {900, 900, 900, 0, 900},
        {9, 3, 2, 2, 0}
    };

    @Test
    public void maxTimeLimit() {
        int[] bunnies = Solution.solution(gMax, 999);

        Assert.assertArrayEquals(new int[]{0, 1, 2}, bunnies);
    }

    public static final int[][] g3Loops1 = new int[][]{
        {0,  1,  10, 10, 10, 10, 10},
        {-2, 0,  10, 10, 10, 10, 10},
        {10, 10, 0,  10, 10, 10, 10},
        {10, 10, 10, 0,  10, 10, 10},
        {10, 10, 10, 10, 0,  10, 10},
        {10, 10, 10, 10, 10, 0,  10},
        {10, 10, 10, 10, 10, 10, 0 },
    };

    public static final int[][] g3Loops2 = new int[][]{
        { 0, 10, 10, 10, 10, 10, 10},
        {10,  0, -2, 10, 10, 10, 10},
        {10, 10,  0, 3, 10, 10, 10},
        {10, 10, 10,  0, -1, 10, 10},
        {10, -1, 10, 10,  0, 10, 10},
        {10, 10, 10, 10, 10,  0, 10},
        {10, 10, 10, 10, 10, 10, 0 },
    };

    @Test
    public void testN() {
        int[] bunnies = Solution.solution(g3Loops1, 1);

        Assert.assertArrayEquals(new int[]{0, 1, 2, 3, 4}, bunnies);
    }

    @Test
    public void loopsDetector() {
        Solution.Digraph g1 = new Solution.Digraph(g1Easy);
        Solution.Digraph g2 = new Solution.Digraph(g2Negatives);
        Solution.Digraph g31 = new Solution.Digraph(g3Loops1);
        Solution.Digraph g32 = new Solution.Digraph(g3Loops2);

        Solution.LoopsDetector finder31 = new Solution.LoopsDetector(g31);
        Assert.assertTrue(finder31.hasNegativeLoops());

        Solution.LoopsDetector finder32 = new Solution.LoopsDetector(g32);
        Assert.assertTrue(finder32.hasNegativeLoops());

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

    public static final int[][] g5moreMoves = new int[][]{
    //    S    0    1   2   3   4   5   G
        { 0,  10,   1, 10,  1, 10,  1,  1}, // Start
        { 1,   0,  10, 10, 10, 10, 10, 10}, // 0
        {10,   1,   0, 10, 10, 10, 10, 10}, // 1
        { 1,  10,  10,  0, 10, 10, 10, 10}, // 2
        {10,  10,  10,  1,  0, 10, 10, 10}, // 3
        { 1,  10,  10, 10, 10,  0, 10, 10}, // 4
        {10,  10,  10, 10, 10,  1,  0, 10}, // 5
        {10,  10,  10, 10, 10, 10, 10,  0}, // Gate
    };

    @Test
    public void moreMoves1() {
        int[] bunnies11 = Solution.solution(g5moreMoves, 3);
        Assert.assertArrayEquals(new int[]{}, bunnies11);
    }

    @Test
    public void moreMoves2() {
        int[] bunnies12 = Solution.solution(g5moreMoves, 4);
        Assert.assertArrayEquals(new int[]{0, 1}, bunnies12);
    }

    @Test
    public void moreMoves3() {
        int[] bunnies21 = Solution.solution(g5moreMoves, 7);
        Assert.assertArrayEquals(new int[]{0, 1, 2, 3}, bunnies21);
    }

    @Test
    public void moreMoves4() {
        int[] bunnies22 = Solution.solution(g5moreMoves, 8);
        Assert.assertArrayEquals(new int[]{0, 1, 2, 3}, bunnies22);
    }

    @Test
    public void moreMoves5() {
        int[] bunnies3 = Solution.solution(g5moreMoves, 10);
        Assert.assertArrayEquals(new int[]{0, 1, 2, 3, 4, 5}, bunnies3);
    }
}

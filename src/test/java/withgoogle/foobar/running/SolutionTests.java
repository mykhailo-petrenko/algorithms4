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
//        print( Solution.allShortestPath(g2Negatives) );

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

    public static int[][] copy(int[][] source) {
        int N = source.length;
        int[][] dest = new int[N][N];

        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                dest[i][j] = source[i][j];
            }
        }

        return dest;
    }

    @Test
    public void loopsDetector() {
        int[][] g1 = copy(g1Easy);
        int[][] g2 = copy(g2Negatives);
        int[][] g31 = copy(g3Loops1);
        int[][] g32 = copy(g3Loops2);

        Solution.allShortestPath(g1);
        Assert.assertFalse(Solution.hasCycles(g1));

        Solution.allShortestPath(g2);
        Assert.assertFalse(Solution.hasCycles(g2));

        Solution.allShortestPath(g31);
        Assert.assertTrue(Solution.hasCycles(g31));

        Solution.allShortestPath(g32);
        Assert.assertTrue(Solution.hasCycles(g32));

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

    public static final int[][] empty = new int [][]{
        {0, 1},
        {1, 0}
    };

    @Test
    public void emptyTest() {
        int[] bunnies = Solution.solution(empty, 1);
        Assert.assertArrayEquals(new int[]{}, bunnies);
    }

    public static final int[][] empty2 = new int [][]{{}};

    @Test
    public void emptyTest2() {
        int[] bunnies = Solution.solution(empty2, 1);
        Assert.assertArrayEquals(new int[]{}, bunnies);
    }

    public static final int[][] oneBunny = new int [][]{
        {1, 1, 1},
        {1, 1, 1},
        {1, 1, 1}
    };

    @Test
    public void oneBunnyTest() {
        int[] bunnies = Solution.solution(oneBunny, 2);
        Assert.assertArrayEquals(new int[]{0}, bunnies);
    }

    public static final int[][] unos = new int[][]{
        {0,  1,  1, 1, 1, 1, 1},
        {1,  1,  1, 1, 1, 1, 1},
        {1,  1,  1, 1, 1, 1, 1},
        {1,  1,  1, 1, 1, 1, 1},
        {1,  1,  1, 1, 1, 1, 1},
        {1,  1,  1, 1, 1, 1, 1},
        {1,  1,  1, 1, 1, 1, 1}
    };

    @Test
    public void unosUnreachable() {
        int[] bunnies = Solution.solution(unos, 1);
        Assert.assertArrayEquals(new int[]{}, bunnies);
    }
}

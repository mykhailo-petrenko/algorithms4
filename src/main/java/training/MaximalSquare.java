package training;

/**
 * Maximal Square
 * ============
 *
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
 *
 * Example:
 *
 * Input:
 *
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 *
 * Output: 4
 */
public class MaximalSquare {
    public static final int W = 0;
    public static final int H = 1;
    public static final int S = 2;

    public int maximalSquare(char[][] matrix) {
        int M = matrix.length;
        if (M == 0) {
            return 0;
        }
        int N = matrix[0].length;
        if (N == 0) {
            return 0;
        }

        int max = 0;
        int[][][]s = new int[M][N][3];

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (i == 0 || j == 0 || matrix[i][j] == '0') {
                    int v = (matrix[i][j] == '0') ? 0 : 1;
                    s[i][j] = new int[]{v, v, v};
                    max = Math.max(max, s[i][j][S]);
                    continue;
                }

                s[i][j] = new int[]{
                    s[i - 1][j][W] + 1,
                    s[i][j - 1][H] + 1,
                    1
                };

                int maxDimension = 1 + s[i - 1][j - 1][S];
                int dimension = Math.min(s[i][j][W], s[i][j][H]);
                s[i][j][S] = Math.min(dimension, maxDimension);

                max = Math.max(max, s[i][j][S]);
            }
        }
        return max * max;
    }
}

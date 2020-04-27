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
        int[][]s = new int[M][N];

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (i == 0 || j == 0 || matrix[i][j] == '0') {
                    int v = (matrix[i][j] == '1') ? 1 : 0;
                    s[i][j] = v;
                    max = Math.max(max, s[i][j]);
                    continue;
                }

                int maxDimension = s[i - 1][j - 1];
                int dimension = Math.min(s[i - 1][j], s[i][j - 1]);
                s[i][j] = 1 + Math.min(dimension, maxDimension);

                max = Math.max(max, s[i][j]);
            }
        }
        return max * max;
    }
}

package training;

/**
 * Number of Islands
 * (LeetCode challenge)[https://leetcode.com/explore/featured/card/30-day-leetcoding-challenge/530/week-3/3302/]
 *
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 *
 */
public class NumIslands {
    public static int numIslands(char[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int M = grid[0].length;
        int N = grid.length;
        int U = M*N + 1;
        int[] union = new int[U];

        for (int x = 0; x < M; x++) {
            for (int y = 0; y < N; y++) {
                int u = x * N + y;

                if (grid[y][x] == '0') {
                    union[u] = -1;
                    continue;
                } else {
                    union[u] = u;
                }

                if (x > 0) {
                    if (grid[y][x - 1] == '1') {
                        join(union, u, ((x-1)*N) + y);
                    }
                }

                if (y > 0) {
                    if (grid[y - 1][x] == '1') {
                        join(union, u, u - 1);
                    }
                }
            }
        }

        int count = 0;
        for (int i = 0; i < U; i++) {
            if (union[i] == i) {
                count++;
            }
        }

        return count;
    }

    public static int getGroupId(int[] tree, int targetId) {
        while (tree[targetId] != targetId) {
            targetId = tree[targetId];
        }
        return targetId;
    }


    public static int join(int[] tree, int nodeId, int targetId) {
        targetId = getGroupId(tree, targetId);
        nodeId =  getGroupId(tree, nodeId);

        if (targetId > nodeId) {
            int b = nodeId;
            nodeId = targetId;
            targetId = b;
        }

        tree[nodeId] = targetId;

        return targetId;
    }

    /**
     * see NumIslandsTests.java for more test cases
     * @param args
     */
    public static void main(String[] args) {
        char[][] map = new char[][] {
            {'1', '1', '1'},
            {'0', '1', '0'},
            {'1', '1', '1'}
        };
        System.out.println(
            numIslands(map)
        );
    }
}

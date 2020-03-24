package training.uf;

import java.util.HashMap;
import java.util.Map;

/**
 * 947. Most Stones Removed with Same Row or Column
 * https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/
 *
 * On a 2D plane, we place stones at some integer coordinate points.  Each coordinate point may have at most one stone.
 *
 * Now, a move consists of removing a stone that shares a column or row with another stone on the grid.
 *
 * What is the largest possible number of moves we can make?
 *
 * Also known as:
 *
 * Remove Marbles
 * ================
 * There is a table/grid with marbles/points.
 * You can remove one of the two marbles,
 * if and only if these two marbles are on the same vertical/horizontal line (with same x or y).
 * You are given a list of marble.
 * Please tell me the minimum number of marbles left on the table.
 *
 * Test cases
 * ================
 * Please see tests/training.uf.RemoveMarblesTests
 * - interviewTest1
 * - interviewTest2
 * - interviewTest12Extended
 *
 */
public class RemoveMarbles {
    public int removeStones(int[][] stones) {
        int[] groups = new int[stones.length];
        int[] groupsPower = new int[stones.length];

        for (int i = 0; i < stones.length; i++) {
            groups[i] = i;
            groupsPower[i] = 1;
        }

        Map<Integer, Integer> groupByX = new HashMap<>();
        Map<Integer, Integer> groupByY = new HashMap<>();

        for (int i = 0; i < stones.length; i++) {
            int groupX = i;

            if (!groupByX.containsKey(stones[i][0])) {
                groupByX.put(stones[i][0], i);
            } else {
                groupX = groupByX.get(stones[i][0]);
            }

            addNode(groups, groupsPower, i, groupX);
        }

        for (int i = 0; i < stones.length; i++) {
            int groupY = i;

            if (!groupByY.containsKey(stones[i][1])) {
                groupByY.put(stones[i][1], i);
            } else {
                groupY = groupByY.get(stones[i][1]);
            }

            addNode(groups, groupsPower, i, groupY);
        }

        int count = 0;

        for (int i = 0; i < stones.length; i++) {
            if (groups[i] == i) {
                count = count + (groupsPower[i] - 1);
            }
        }

        return count;
    }

    public void addNode(int[] groups, int[] groupsPower, int child, int parent) {
        while (groups[child] != child) {
            child = groups[child];
        }

        while (groups[parent] != parent) {
            parent = groups[parent];
        }

        if (parent == child) {
            return;
        }

        groups[child] = parent;
        groupsPower[parent] += groupsPower[child];
    }
}

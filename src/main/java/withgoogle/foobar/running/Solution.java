package withgoogle.foobar.running;

import java.util.ArrayList;
import java.util.List;

/**
 * Running with Bunnies
 * ====================
 * <p>
 * You and your rescued bunny prisoners need to get out of this collapsing death trap of a space station - and fast!
 * Unfortunately, some of the bunnies have been weakened by their long imprisonment and can't run very fast.
 * Their friends are trying to help them, but this escape would go a lot faster if you also pitched in.
 * The defensive bulkhead doors have begun to close, and if you don't make it through in time, you'll be trapped!
 * You need to grab as many bunnies as you can and get through the bulkheads before they close.
 * <p>
 * The time it takes to move from your starting point to all of the bunnies and
 * to the bulkhead will be given to you in a square matrix of integers.
 * Each row will tell you the time it takes to get to the start, first bunny, second bunny, ..., last bunny, and the bulkhead in that order.
 * The order of the rows follows the same pattern (start, each bunny, bulkhead).
 * The bunnies can jump into your arms, so picking them up is instantaneous,
 * and arriving at the bulkhead at the same time as it seals still allows for a successful, if dramatic, escape.
 * (Don't worry, any bunnies you don't pick up will be able to escape with you since they no longer have to carry the ones you did pick up.)
 * You can revisit different spots if you wish, and moving to the bulkhead doesn't mean you have to immediately leave - you can move to
 * and from the bulkhead to pick up additional bunnies if time permits.
 * <p>
 * In addition to spending time traveling between bunnies,
 * some paths interact with the space station's security checkpoints and add time back to the clock.
 * Adding time to the clock will delay the closing of the bulkhead doors, and if the time goes back up to 0 or a positive number after the doors have already closed,
 * it triggers the bulkhead to reopen.
 * Therefore, it might be possible to walk in a circle and keep gaining time: that is, each time a path is traversed,
 * the same amount of time is used or added.
 * <p>
 * Write a function of the form solution(times, time_limit) to calculate the most bunnies you can pick up and which bunnies they are,
 * while still escaping through the bulkhead before the doors close for good.
 * If there are multiple sets of bunnies of the same size, return the set of bunnies with the lowest prisoner IDs (as indexes) in sorted order.
 * The bunnies are represented as a sorted list by prisoner ID, with the first bunny being 0.
 * There are at most 5 bunnies, and time_limit is a non-negative integer that is at most 999.
 * <p>
 * For instance, in the case of
 * [
 * [0, 2, 2, 2, -1],  # 0 = Start
 * [9, 0, 2, 2, -1],  # 1 = Bunny 0
 * [9, 3, 0, 2, -1],  # 2 = Bunny 1
 * [9, 3, 2, 0, -1],  # 3 = Bunny 2
 * [9, 3, 2, 2,  0],  # 4 = Bulkhead
 * ]
 * and a time limit of 1, the five inner array rows designate the starting point, bunny 0, bunny 1, bunny 2, and the bulkhead door exit respectively.
 * You could take the path:
 * <p>
 * Start End Delta Time Status
 * -   0     -    1 Bulkhead initially open
 * 0   4    -1    2
 * 4   2     2    0
 * 2   4    -1    1
 * 4   3     2   -1 Bulkhead closes
 * 3   4    -1    0 Bulkhead reopens; you and the bunnies exit
 * <p>
 * With this solution, you would pick up bunnies 1 and 2. This is the best combination for this space station hallway, so the answer is [1, 2].
 * <p>
 * Test cases
 * ==========
 * <p>
 * Input:
 * Solution.solution({
 * {0, 1, 1, 1, 1},
 * {1, 0, 1, 1, 1},
 * {1, 1, 0, 1, 1},
 * {1, 1, 1, 0, 1},
 * {1, 1, 1, 1, 0}}, 3)
 * Output:
 * [0, 1]
 * <p>
 * Input:
 * Solution.solution({
 * {0, 2, 2, 2, -1},
 * {9, 0, 2, 2, -1},
 * {9, 3, 0, 2, -1},
 * {9, 3, 2, 0, -1},
 * {9, 3, 2, 2, 0}}, 1)
 * Output:
 * [1, 2]
 */
public class Solution {

    public static int[] allBunnies(int maxBunnies) {
        int[] allBunnies = new int[maxBunnies];

        for (int i = 0; i < maxBunnies; i++) {
            allBunnies[i] = i;
        }

        return allBunnies;
    }

    /**
     * Floyd–Warshall
     * In O(n^3) time will be generated matrix with all shortest paths weights between each vertex
     * @return int[][]
     */
    public static int[][] allShortestPath(int[][] times) {
        int V = times.length;

        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    int weight = times[i][k] + times[k][j];
                    if (times[i][j] > weight) {
                        times[i][j] = weight;
                    }
                }
            }
        }

        return times;
    }

    /**
     * Detect the negative loops presence
     * just run one external loop iteration after Floyd–Warshall
     * @return true if negative loops are present, false if no
     */
    public static boolean hasCycles(int[][] times) {
        int V = times.length;

        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < 1; j++) {
                    int weight = times[i][k] + times[k][j];
                    if (times[i][j] > weight) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * generateCombinations helper
     * @param combinations
     * @param data
     * @param start
     * @param end
     * @param index
     */
    public static void helper(List<int[]> combinations, int data[], int start, int end, int index) {
        if (index == data.length) {
            int[] combination = data.clone();
            combinations.add(combination);
        } else if (start <= end) {
            data[index] = start;
            helper(combinations, data, start + 1, end, index + 1);
            helper(combinations, data, start + 1, end, index);
        }
    }

    /**
     * Combinations of `r` element from `n` generator
     * @param n elements count from 0 to n
     * @param r combination size
     * @return List<int[]> combinations list
     */
    public static List<int[]> generateCombinations(int n, int r) {
        List<int[]> combinations = new ArrayList<>();
        helper(combinations, new int[r], 0, n - 1, 0);
        return combinations;
    }

    /**
     * Generate permutations for income list of element
     * @param arr
     * @return
     */
    public static List<List<Integer>> permute(int[] arr) {
        List<List<Integer>> list = new ArrayList<>();
        permuteHelper(list, new ArrayList<>(), arr);
        return list;
    }

    public static void permuteHelper(List<List<Integer>> list, List<Integer> resultList, int[] arr) {

        // Base case
        if (resultList.size() == arr.length) {
            list.add(new ArrayList<>(resultList));
        } else {
            for (int i = 0; i < arr.length; i++) {

                if (resultList.contains(arr[i])) {
                    continue;
                }

                resultList.add(arr[i]);
                permuteHelper(list, resultList, arr);
                resultList.remove(resultList.size() - 1);
            }
        }
    }

    public static int[] solution(int[][] times, int times_limit) {
        if (times.length < 3) {
            return new int[]{};
        }

        // Convert graph to matrix with shortest paths for each vertex
        times = allShortestPath(times);

        int maxBunnies = times.length - 2;
        int gateId = times.length - 1;

        // All bunnie available because negative loop
        if (hasCycles(times)) {
            return allBunnies(maxBunnies);
        }

        // Check all bunnies combinations and permutation.
        for (int n = maxBunnies; n > 0; n--) {
            List<int[]> combinations = generateCombinations(maxBunnies, n);
            for (int[] combination : combinations) {
                int tail = combination.length - 1;

                for (List<Integer> mutation : permute(combination)) {
                    int time = times[0][mutation.get(0) + 1] + times[mutation.get(tail) + 1][gateId];

                    for (int b = 1; b <= tail; b++) {
                        time += times[mutation.get(b - 1) + 1][mutation.get(b) + 1];
                    }

                    // Exit with current combination
                    // because combinations generated in DESC order by length and bunnies in ASC order by id
                    if (time <= times_limit) {
                        return combination;
                    }
                }
            }
        }

        return new int[]{};
    }

}

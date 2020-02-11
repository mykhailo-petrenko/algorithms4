package withgoogle.foobar.running;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.Set;
import java.util.TreeSet;

/**
 * Running with Bunnies
 * ====================
 *
 * You and your rescued bunny prisoners need to get out of this collapsing death trap of a space station - and fast!
 * Unfortunately, some of the bunnies have been weakened by their long imprisonment and can't run very fast.
 * Their friends are trying to help them, but this escape would go a lot faster if you also pitched in.
 * The defensive bulkhead doors have begun to close, and if you don't make it through in time, you'll be trapped!
 * You need to grab as many bunnies as you can and get through the bulkheads before they close.
 *
 * The time it takes to move from your starting point to all of the bunnies and
 * to the bulkhead will be given to you in a square matrix of integers.
 * Each row will tell you the time it takes to get to the start, first bunny, second bunny, ..., last bunny, and the bulkhead in that order.
 * The order of the rows follows the same pattern (start, each bunny, bulkhead).
 * The bunnies can jump into your arms, so picking them up is instantaneous,
 * and arriving at the bulkhead at the same time as it seals still allows for a successful, if dramatic, escape.
 * (Don't worry, any bunnies you don't pick up will be able to escape with you since they no longer have to carry the ones you did pick up.)
 * You can revisit different spots if you wish, and moving to the bulkhead doesn't mean you have to immediately leave - you can move to
 * and from the bulkhead to pick up additional bunnies if time permits.
 *
 * In addition to spending time traveling between bunnies,
 * some paths interact with the space station's security checkpoints and add time back to the clock.
 * Adding time to the clock will delay the closing of the bulkhead doors, and if the time goes back up to 0 or a positive number after the doors have already closed,
 * it triggers the bulkhead to reopen.
 * Therefore, it might be possible to walk in a circle and keep gaining time: that is, each time a path is traversed,
 * the same amount of time is used or added.
 *
 * Write a function of the form solution(times, time_limit) to calculate the most bunnies you can pick up and which bunnies they are,
 * while still escaping through the bulkhead before the doors close for good.
 * If there are multiple sets of bunnies of the same size, return the set of bunnies with the lowest prisoner IDs (as indexes) in sorted order.
 * The bunnies are represented as a sorted list by prisoner ID, with the first bunny being 0.
 * There are at most 5 bunnies, and time_limit is a non-negative integer that is at most 999.
 *
 * For instance, in the case of
 * [
 *   [0, 2, 2, 2, -1],  # 0 = Start
 *   [9, 0, 2, 2, -1],  # 1 = Bunny 0
 *   [9, 3, 0, 2, -1],  # 2 = Bunny 1
 *   [9, 3, 2, 0, -1],  # 3 = Bunny 2
 *   [9, 3, 2, 2,  0],  # 4 = Bulkhead
 * ]
 * and a time limit of 1, the five inner array rows designate the starting point, bunny 0, bunny 1, bunny 2, and the bulkhead door exit respectively.
 * You could take the path:
 *
 * Start End Delta Time Status
 *     -   0     -    1 Bulkhead initially open
 *     0   4    -1    2
 *     4   2     2    0
 *     2   4    -1    1
 *     4   3     2   -1 Bulkhead closes
 *     3   4    -1    0 Bulkhead reopens; you and the bunnies exit
 *
 * With this solution, you would pick up bunnies 1 and 2. This is the best combination for this space station hallway, so the answer is [1, 2].
 *
 * Test cases
 * ==========
 *
 * Input:
 * Solution.solution({
 * {0, 1, 1, 1, 1},
 * {1, 0, 1, 1, 1},
 * {1, 1, 0, 1, 1},
 * {1, 1, 1, 0, 1},
 * {1, 1, 1, 1, 0}}, 3)
 * Output:
 *     [0, 1]
 *
 * Input:
 * Solution.solution({
 * {0, 2, 2, 2, -1},
 * {9, 0, 2, 2, -1},
 * {9, 3, 0, 2, -1},
 * {9, 3, 2, 0, -1},
 * {9, 3, 2, 2, 0}}, 1)
 * Output:
 *     [1, 2]
 */
public class Solution {

    public static class Edge {
        public int from;
        public int to;
        public int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return String.format("[%d->%d: %d]", from, to, weight);
        }
    }

    public static class Digraph {
        private ArrayList<Edge>[] g;
        private int V;
        private int E;
        private ArrayList<Edge> negativeEdges;
        private int maxWeight;
        private int minWeight;

        public Digraph(int times[][]) {
            E = 0;
            negativeEdges = new ArrayList<Edge>();
            maxWeight = 0;
            minWeight = Integer.MAX_VALUE;
            V = times.length;
            g = (ArrayList<Edge>[])new ArrayList[times.length];

            for (int v = 0; v < times.length; v++) {
                g[v] = new ArrayList<Edge>();

                for (int w = 0; w < times[v].length; w++) {
                    int weight = times[v][w];

                    if (v == w) {
                        continue;
                    }

                    Edge edge = new Edge(v, w, weight);

                    g[v].add(edge);
                    E++;

                    if (maxWeight < weight) {
                        maxWeight = weight;
                    }

                    if (minWeight > weight) {
                        minWeight = weight;
                    }

                    if (weight < 0) {
                        negativeEdges.add(edge);
                    }
                }
            }
        }

        public int V() {
            return V;
        }

        public int E() {
            return E;
        }

        public Iterable<Edge> negativeEdges() {
            return negativeEdges;
        }

        public Iterable<Edge> adj(int v) {
            return g[v];
        }
    }

    public static class LoopsDetector {
        private Digraph g;

        public LoopsDetector(Digraph graph) {
            g = graph;
        }

        public boolean hasNegativeLoops() {
            for (Edge e: g.negativeEdges()) {
                if (checkEdge(e) == true) {
                    return true;
                }
            }

            return false;
        }

        public static class Task {
            public int time;
            public Edge to;

            public Task(int time, Edge to) {
                this.time = time;
                this.to = to;
            }
        }

        public boolean checkEdge(Edge e) {
            int s = e.from;

            Deque<Task> toVisit = new ArrayDeque<>();

            toVisit.addLast(new Task(0, e));

            while (!toVisit.isEmpty()) {
                Task task = toVisit.pollFirst();
                int v = task.to.to;
                int time = task.time + task.to.weight;

                for (Edge edge: g.adj(v)) {
                    int nextTime = edge.weight + time;

                    if (nextTime >= 0) {
                        continue;
                    }

                    if (v == s) {
                        return true;
                    }

                    toVisit.addLast(new Task(time, edge));
                }
            }

            return false;
        }
    }

    public static class State {
        public int time;
        public int v;
        public Set<Integer> bunnies;

        public State(int time, int v, Set<Integer> bunnies) {
            this.time = time;
            this.v = v;
            this.bunnies = bunnies;
        }

        @Override
        public String toString() {
            String bunnies = "";
            if (this.bunnies != null) {
                bunnies = bunniesToString(this.bunnies);
            }

            return String.format("t: %d, v: %d, bunnies: %s", time, v, bunnies);
        }
    }

    public static String bunniesToString(Set<Integer> bunnies) {
        String bs = "[";
        for (int b: bunnies) {
            bs += " " + b;
        }
        bs += " ]";

        return bs;
    }

    public static class BunniesComparator implements Comparator<Set<Integer>> {
        @Override
        public int compare(Set<Integer> o1, Set<Integer> o2) {

            Object[] a1 = o1.toArray();
            Object[] a2 = o2.toArray();

            for (int i=0; i<a1.length; i++) {
                int diff = (int)a1[i] - (int)a2[i];

                if (diff == 0) {
                    continue;
                }

                return diff;
            }

            return 0;
        }
    }

    public static int[] allBunnies(int maxBunnies) {
        int[] allBunnies = new int[maxBunnies];

        for (int i = 0; i < maxBunnies; i++) {
            allBunnies[i] = i;
        }

        return allBunnies;
    }

    /**
     * Floyd–Warshall
     * @return
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

    public static int[] solution(int[][] times, int times_limit) {
        BunniesComparator bc = new BunniesComparator();
        int bunniesInResults = 0;
        Set<Integer> answer = new TreeSet<>();
        Digraph g = new Digraph(allShortestPath(times));
        int maxBunnies = g.V() - 2;
        int gateId = g.V() - 1;

        if (g.minWeight < 0) {
            if ((new LoopsDetector(g)).hasNegativeLoops()) {
                return allBunnies(maxBunnies);
            }
        }

        Deque<State> toVisit = new ArrayDeque<>();
        toVisit.addLast(new State(0, 0, new TreeSet<>()));

        while (!toVisit.isEmpty()) {
            State state = toVisit.pollFirst();

            for (Edge e: g.adj(state.v)) {
                int time = state.time + e.weight;
                int v = e.to;
                Set<Integer> bunnies = state.bunnies;

                if (v == 0) {
                    continue;
                }

                if (bunnies.contains(v - 1)) {
                    continue;
                }

                if (v == gateId) {
                    if (time <= times_limit) {
                        if (bunnies.size() == maxBunnies) {
                            return allBunnies(maxBunnies);
                        }

                        if (bunniesInResults < bunnies.size()) {
                            bunniesInResults = bunnies.size();
                            answer = bunnies;
                        } else if (bunniesInResults == bunnies.size() && bc.compare(bunnies, answer) < 0) {
                            answer = bunnies;
                        }
                    }

                    continue;
                }

                bunnies = new TreeSet<>(state.bunnies);
                bunnies.add(v - 1);

                State newState = new State(time, v, bunnies);

                toVisit.addLast(newState);
            }
        }

        int[] result = new int[answer.size()];
        int i = 0;
        for (int b: answer) {
            result[i] = b;
            i++;
        }

        return result;
    }
}

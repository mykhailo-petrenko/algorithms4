package training.path;

import java.util.*;

/**
 * Cheapest Flights Within K Stops
 * ===============================
 *
 * There are n cities connected by m flights. Each flight starts from city u and arrives at v with a price w.
 *
 * Now given all the cities and flights, together with starting city src and the destination dst,
 * your task is to find the cheapest price from src to dst with up to k stops.
 * If there is no such route, output -1.
 *
 * Constraints:
 *
 * - The number of nodes n will be in range [1, 100], with nodes labeled from 0 to n - 1.
 * - The size of flights will be in range [0, n * (n - 1) / 2].
 * - The format of each flight will be (src, dst, price).
 * - The price of each flight will be in the range [1, 10000].
 * - k is in the range of [0, n - 1].
 * -    There will not be any duplicated flights or self cycles.
 */
public class FindCheapestPrice {
    private static class Edge {
        int from;
        int to;
        int price;

        public Edge(int[] e) {
            this.from = e[0];
            this.to = e[1];
            this.price = e[2];
        }
    }

    private static class Stop {
        int edge;
        int stops;
        int totalPrice;

        public Stop(int edge, int stops, int totalPrice) {
            this.edge = edge;
            this.stops = stops;
            this.totalPrice = totalPrice;
        }
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        ArrayList<Edge>[] graph = (ArrayList<Edge>[]) new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] f : flights) {
            graph[f[0]].add(new Edge(f));
        }

        Queue<Stop> queue = new PriorityQueue<>((o1, o2) -> o1.totalPrice - o2.totalPrice);
        queue.add(new Stop(src, 0, 0));

        while (!queue.isEmpty()) {
            Stop s = queue.poll();

            if (s.edge == dst) {
                return s.totalPrice;
            }

            if (s.stops > K) {
                continue;
            }

            for (Edge e : graph[s.edge]) {
                queue.add(new Stop(e.to, s.stops + 1, s.totalPrice + e.price));
            }
        }

        return -1;
    }
}

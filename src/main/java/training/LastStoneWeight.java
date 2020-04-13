package training;

import java.util.PriorityQueue;

public class LastStoneWeight {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> q = new PriorityQueue<Integer>(
            stones.length,
            (o1, o2) -> o2 - o1
        );

        for (int i = 0; i < stones.length; i++) {
            q.add(stones[i]);
        }

        while (q.size() > 1) {
            int s = q.poll() - q.poll();

            if (s > 0) {
                q.add(s);
            }
        }

        if (q.isEmpty()) {
            return 0;
        }

        return q.poll();
    }

    public static void main(String[] args) {
        LastStoneWeight solution = new LastStoneWeight();

        int actual = solution.lastStoneWeight(new int[] {2, 7, 4, 1, 8, 1});

        System.out.println(actual);
    }
}

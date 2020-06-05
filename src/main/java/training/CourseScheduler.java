package training;

import java.util.ArrayList;
import java.util.List;


/**
 * Course Schedule
 * ===============
 *
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses-1.
 *
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 *
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 *
 * Constraints:
 *
 *  - The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
 *  - You may assume that there are no duplicate edges in the input prerequisites.
 *  - 1 <= numCourses <= 10^5
 *
 * Solution
 * ========
 *
 * Use Topological Sort with loop detection without stack because no need sorting results.
 *
 */
public class CourseScheduler {
    public static class LoopFinder {
        int N;
        List<Integer>[] g;
        boolean[] visited;
        boolean[] marked;

        public LoopFinder(int n, int[][] prerequisites) {
            this.N = n;
            g = new List[N];
            visited = new boolean[N];
            marked = new boolean[N];

            for (int[] requisite : prerequisites) {
                int from = requisite[0];
                int to = requisite[1];

                if (g[from] == null) {
                    g[from] = new ArrayList<>();
                }

                g[from].add(to);
            }
        }

        public boolean hasLoops() {
            for (int i = 0; i < N; i++) {
                if (hasLoopDfs(i)) {
                    return true;
                }
            }

            return false;
        }

        private boolean hasLoopDfs(int node) {
            if (visited[node]) {
                return true;
            }

            if (marked[node]) {
                return false;
            }

            visited[node] = true;

            if (g[node] != null) {
                for (int child : g[node]) {
                    if (hasLoopDfs(child)) {
                        return true;
                    }
                }
            }

            visited[node] = false;
            marked[node] = true;

            return false;
        }
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        LoopFinder finder = new LoopFinder(numCourses, prerequisites);

        return !finder.hasLoops();
    }
}

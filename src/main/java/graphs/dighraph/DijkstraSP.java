package graphs.dighraph;


import java.util.ArrayDeque;
import java.util.Queue;

public class DijkstraSP extends SP {

    /**
     * Build SP tree
     * @param graph graph
     * @param s source vertex
     */
    public DijkstraSP(EdgeWeightedDigraph graph, int s) {
        this.graph = graph;
        this.s = s;

        initData();

        // @TODO: Implement via Priority Queue
        Queue<Integer> q = new ArrayDeque<>();

        q.add(s);

        while (!q.isEmpty()) {
            int v = q.poll();

            for (DirectedEdge e: graph.adj(v)) {
                if (relaxEdge(e)) {
                    q.add(e.to());
                }
            }
        }
    }
}

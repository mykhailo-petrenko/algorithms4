package graphs.dighraph;

import java.util.ArrayList;

public class EdgeWeightedDigraph {
    private final int V;
    private int E;
    private ArrayList<DirectedEdge>[] adj;

    /**
     * Empty V-vertex Graph
     * @param V number of vertices
     */
    public EdgeWeightedDigraph(int V) {
        this.V = V;
        E = 0;

        adj = (ArrayList<DirectedEdge>[]) new ArrayList[V];
        for (int i=0; i<V; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    /**
     * Number of vertices
     * @return int
     */
    int V() {
        return V;
    }

    /**
     * Number of edges
     * @return int
     */
    int E() {
        return E;
    }

    /**
     * Add edge
     * @param edge
     */
    public void addEdge(DirectedEdge edge) {
        adj[edge.from()].add(edge);
        E++;
    }

    public void addEdge(int v, int w, double weight) {
        addEdge(new DirectedEdge(v, w, weight));
    }

    public Iterable<DirectedEdge> adj(int v) {
        return adj[v];
    }

    public Iterable<DirectedEdge> edges() {
        ArrayList<DirectedEdge> edges = new ArrayList<>();

        for (int v = 0; v < V; v++) {
            for (DirectedEdge e: adj[v]) {
                edges.add(e);
            }
        }

        return edges;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        s.append(String.format("V: %d, E: %d\r\n", V, E));

        for (int v = 0; v < V; v++) {
            s.append(String.format("%d: ", v));

            for (DirectedEdge e: adj[v]) {
                s.append(" ");
                s.append(e);
            }

            s.append("\r\n");
        }

        return s.toString();
    }
}

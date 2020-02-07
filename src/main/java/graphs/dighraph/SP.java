package graphs.dighraph;

import java.util.Stack;

public abstract class SP {
    protected EdgeWeightedDigraph graph;
    protected int s;
    protected DirectedEdge[] edgeTo;
    protected double[] distTo;

    public Iterable<DirectedEdge> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }

        Stack<DirectedEdge> path = new Stack<>();

        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }

        return path;
    }

    public double distTo(int v) {
        return distTo[v];
    }

    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    protected boolean relaxEdge(DirectedEdge e) {
        int v = e.from();
        int w = e.to();
        double potentialWeight = distTo[v] + e.weight();

        if (distTo[w] > potentialWeight) {
            distTo[w] = potentialWeight;
            edgeTo[w] = e;

            return true;
        }

        return false;
    }

    protected void initData() {
        int V = graph.V();
        edgeTo = new DirectedEdge[V];
        distTo = new double[V];

        for (int i = 0; i < V; i++) {
            edgeTo[i] = null;
            distTo[i] = Double.POSITIVE_INFINITY;
        }

        distTo[s] = 0;
    }
}

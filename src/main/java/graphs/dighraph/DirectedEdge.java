package graphs.dighraph;

public class DirectedEdge implements Comparable<DirectedEdge> {
    private final int v;
    private final int w;
    private final double weight;

    public DirectedEdge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int from() {
        return v;
    }

    public int to() {
        return w;
    }

    public double weight() {
        return weight;
    }

    @Override
    public int compareTo(DirectedEdge o) {
        double diff = weight - o.weight();

        if (diff < 0) {
            return -1;
        } else if (diff > 0) {
            return 1;
        }

        return 0;
    }

    @Override
    public String toString() {
        return String.format("%d->%d %.2f", v, w, weight);
    }
}

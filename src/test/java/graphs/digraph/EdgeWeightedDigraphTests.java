package graphs.digraph;

import org.junit.Test;
import edu.princeton.cs.introcs.In;
import graphs.dighraph.*;

public class EdgeWeightedDigraphTests {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.printf("Please define path to file, from and to");
            System.exit(1);
        }

        String graphSource = args[0];
        int pathFrom = Integer.parseInt(args[1]);
        int pathTo = Integer.parseInt(args[2]);

        EdgeWeightedDigraph g = loadGraph(graphSource);

        SP path = new DijkstraSP(g, pathFrom);

        if (path.hasPathTo(pathTo)) {
           double distance = path.distTo(pathTo);

            System.out.printf("Distance: %f\n", distance);

            for (DirectedEdge e: path.pathTo(pathTo)) {
                System.out.printf("[%d <- %d]", e.to(), e.from());
            }

            System.out.println("");
        } else {
            System.out.println("has no path");
        }
    }

    public static EdgeWeightedDigraph loadGraph(String fileName) {
        In f = new In(fileName);

        int V = f.readInt();
        int E = f.readInt();

        EdgeWeightedDigraph g = new EdgeWeightedDigraph(V);

        for (int i = 0; i < E; i++) {
            g.addEdge(f.readInt(), f.readInt(), f.readDouble());
        }

        System.out.printf(g.toString());

        return g;
    }

    @Test
    public void basic() {

        EdgeWeightedDigraph g = new EdgeWeightedDigraph(4);

        g.addEdge(0, 1, 10);
        g.addEdge(0, 2, 20);

        g.addEdge(1, 3, 2);
        g.addEdge(2, 3, 2);

        g.addEdge(1, 3, 50);

        g.addEdge(3, 0, -1);

        System.out.println(g);
    }
}

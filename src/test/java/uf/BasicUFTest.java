package uf;

import edu.princeton.cs.algorithms.Counter;
import edu.princeton.cs.introcs.StdDraw;
import edu.princeton.cs.introcs.Stopwatch;
import org.junit.Assert;
import org.junit.Test;

public class BasicUFTest {
    static {
        String path = BasicUFTest.class
                .getClassLoader()
                .getResource("logging.properties")
                .getFile();
        System.setProperty("java.util.logging.config.file", path);
    }

    private static class BasicUFReader extends UFReader<BasicUF> {
        public BasicUFReader(String filePath) {
            super(filePath);
        }

        @Override
        public BasicUF createUFInstance(int N) {
            return new BasicUF(N);
        }
    }

    private static class QuickUnionUFReader extends UFReader<QuickUnionUF> {
        public QuickUnionUFReader(String filePath) {
            super(filePath);
        }

        @Override
        public QuickUnionUF createUFInstance(int N) {
            return new QuickUnionUF(N);
        }
    }

    private static class BalancedQuickUnionUFReader extends UFReader<BalancedQuickUnionUF> {
        public BalancedQuickUnionUFReader(String filePath) {
            super(filePath);
        }

        @Override
        public BalancedQuickUnionUF createUFInstance(int N) {
            return new BalancedQuickUnionUF(N);
        }
    }

    public static void main(String[] args) {

        String filePath = args[0];

        if (filePath==null || filePath.isEmpty()) {
            System.out.println("Please provide path to uf file as first argument.");
            System.exit(1);
        }

        long x, y;
        Counter c;
        Stopwatch sw;

//        UFReader reader = new BasicUFReader(filePath);
//        UFReader reader = new QuickUnionUFReader(filePath);
        UFReader reader = new BalancedQuickUnionUFReader(filePath);

        StdDraw.setXscale(0, 300000);
        StdDraw.setYscale(0, 300000);

        c = new Counter("Basic");
        sw = new Stopwatch();

        while (reader.hasNext()) {
            reader.next();

            c.increment();
            x = c.tally();
            y = (long)(sw.elapsedTime() * 1000);

            StdDraw.point(x, y);
        }
    }

    @Test
    public void basicTest() {
        simpleTest(new BasicUF(10));
    }

    @Test
    public void quickUnionTest() {
        simpleTest(new QuickUnionUF(10));
    }

    @Test
    public void balancedQuickUnionTest() {
        simpleTest(new BalancedQuickUnionUF(10));
    }

    public void simpleTest(UF u) {
        u.union(4, 3);
        u.union(3, 8);
        u.union(6, 5);
        u.union(9, 4);
        u.union(2, 1);

        Assert.assertTrue(u.connected(8, 9));

        u.union(5, 0);
        u.union(7, 2);
        u.union(6, 1);

        Assert.assertTrue(u.connected(1, 0));
        Assert.assertTrue(u.connected(6, 7));
        Assert.assertTrue(u.connected(1, 7));
        Assert.assertTrue(u.connected(8, 4));

        Assert.assertFalse(u.connected(0, 9));
        Assert.assertFalse(u.connected(7, 3));
        Assert.assertFalse(u.connected(7, 8));
        Assert.assertFalse(u.connected(2, 3));
    }
}

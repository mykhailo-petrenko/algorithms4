package uf;

import edu.princeton.cs.introcs.StdIn;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.*;


public abstract class UFReader<T extends UF> {
    private T uf;
    private int N;

    public static final Logger LOGGER = Logger.getLogger(UFReader.class.getName());

    public UFReader(String filePath) {
        try {
            System.setIn(new FileInputStream(filePath));
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
            return;
        }

        N = StdIn.readInt();
        uf = createUFInstance(N);
    }

    public abstract T createUFInstance(int N);

    public int length() {
        return N;
    }

    public boolean hasNext() {
        return !StdIn.isEmpty();
    }

    public void next() {
        int q = StdIn.readInt();
        int p = StdIn.readInt();

        if (uf.connected(q, p)) {
            LOGGER.log(Level.INFO, "Skip %d - %d", new int[]{p, q});
            return;
        }

        uf.union(p, q);
        LOGGER.log(Level.INFO, "Connected %d - %d", new int[]{p, q});
    }

}

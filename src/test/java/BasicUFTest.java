import edu.princeton.cs.introcs.StdIn;
import org.junit.Assert;
import org.junit.Test;
import uf.BasicUF;
import uf.UF;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BasicUFTest {
    public static void main(String[] args) {
        try {
            if (args.length > 1) {
                System.setIn(new FileInputStream(args[1]));
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
            return;
        }

        UF uf = new BasicUF(StdIn.readInt());

        while(!StdIn.isEmpty()) {
            int q = StdIn.readInt();
            int p = StdIn.readInt();

            if (uf.connected(q, p)) {
                System.out.printf("Skip %d - %d\n", p, q);
                continue;
            }

            uf.union(p, q);
            System.out.printf("Connected %d - %d\n", p, q);
        }
    }

    @Test
    public void basicTest() {
        UF u = new BasicUF(10);

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

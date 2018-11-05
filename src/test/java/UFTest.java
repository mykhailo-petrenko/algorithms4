import org.junit.Assert;
import org.junit.Test;
import uf.BasicUF;
import uf.UF;

public class UFTest {
    public static void main(String[] args) {
        int[] a = new int[10];

        a[3] = 1;
        a[5] = 1;

        System.out.println(a[9]);
        System.out.println(a[1]);
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

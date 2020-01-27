package withgoogle.foobar.elevatormaintenance;

import org.junit.Assert;
import org.junit.Test;

public class SolutionTests {
    @Test
    public void comparatorBasicTests() {
        Solution.VersionComparator c = new Solution.VersionComparator();

        Assert.assertTrue(
            c.compare(new int[]{1}, new int[]{1, 0}) < 0
        );

        Assert.assertTrue(
            c.compare(new int[]{2, 0}, new int[]{2, 0}) == 0
        );

        Assert.assertTrue(
            c.compare(new int[]{2, 1}, new int[]{2, 0}) > 0
        );

        Assert.assertTrue(
            c.compare(new int[]{2, 1, 0}, new int[]{2, 1}) > 0
        );

        Assert.assertTrue(
            c.compare(new int[]{1, 1, 12}, new int[]{1, 1, 2}) > 0
        );
    }

    @Test
    public void baseOne() {

         Assert.assertArrayEquals(
             new String[]{"0.1", "1.1.1", "1.2", "1.2.1", "1.9", "2", "2.0", "2.0.0"},
             Solution.solution(new String[]{"1.9", "2.0.0", "1.2", "2", "0.1", "1.2.1", "1.1.1", "2.0"})
         );

         Assert.assertArrayEquals(
             new String[]{"0.1", "1.1.1", "1.2", "1.2.1", "1.11", "2", "2.0", "2.0.0"},
             Solution.solution(new String[]{"1.11", "2.0.0", "1.2", "2", "0.1", "1.2.1", "1.1.1", "2.0"})
         );
    }

    @Test
    public void baseTo() {
        Assert.assertArrayEquals(
            new String[]{"1.0", "1.0.2", "1.0.9", "1.1.2", "1.3.3"},
            Solution.solution(new String[]{"1.1.2", "1.0", "1.3.3", "1.0.9", "1.0.2"})
        );

        Assert.assertArrayEquals(
            new String[]{"1.0", "1.0.2", "1.0.12", "1.1.2", "1.3.3"},
            Solution.solution(new String[]{"1.1.2", "1.0", "1.3.3", "1.0.12", "1.0.2"})
        );
    }

    @Test
    public void onlyByMajor() {
        Assert.assertArrayEquals(
            new String[]{"0", "1.0", "2.0"},
            Solution.solution(new String[]{"2.0", "0", "1.0"})
        );
    }

    @Test
    public void onlyByMajorNadMinor() {
        Assert.assertArrayEquals(
            new String[]{"0", "0.1", "1", "1.2", "1.4"},
            Solution.solution(new String[]{"1.2", "0.1", "0", "1.4", "1"})
        );
    }
}

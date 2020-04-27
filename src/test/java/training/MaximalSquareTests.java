package training;

import org.junit.Assert;
import org.junit.Test;

public class MaximalSquareTests {
    @Test
    public void smock1() {
        char[][] matrix = new char[][]{
            {'1', '0', '1', '0', '0'},
            {'1', '0', '1', '1', '1'},
            {'1', '1', '1', '1', '1'},
            {'1', '0', '0', '1', '0'}
        };

        Assert.assertEquals(
            4,
            new MaximalSquare().maximalSquare(matrix)
        );
    }

    @Test
    public void smock2() {
        char[][] matrix = new char[][]{
            {'1','0','1','0','0','0','0','0'},
            {'1','0','1','1','1','0','0','0'},
            {'1','1','1','1','1','1','1','1'},
            {'1','0','0','1','0','1','1','1'},
            {'0','0','0','0','0','1','1','1'}
        };
        Assert.assertEquals(
            9,
            new MaximalSquare().maximalSquare(matrix)
        );
    }
}

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

    @Test
    public void min() {
        char[][] matrix = new char[][]{{}};
        Assert.assertEquals(
            0,
            new MaximalSquare().maximalSquare(matrix)
        );

        matrix = new char[][]{{'0'}};
        Assert.assertEquals(
            0,
            new MaximalSquare().maximalSquare(matrix)
        );

        matrix = new char[][]{{'1'}};
        Assert.assertEquals(
            1,
            new MaximalSquare().maximalSquare(matrix)
        );
    }

    @Test
    public void zeros() {
        char[][] matrix = new char[][]{
            {'0','0','0','0','0'},
            {'0','0','0','0','0'},
            {'0','0','0','0','0'}
        };
        Assert.assertEquals(
            0,
            new MaximalSquare().maximalSquare(matrix)
        );
    }
}

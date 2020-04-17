package training;

import org.junit.Assert;
import org.junit.Test;

public class NumIslandsTests {
    @Test
    public void basicTest() {
        char[][] map = new char[][] {
            {'1', '1', '1'},
            {'0', '1', '0'},
            {'1', '1', '1'}
        };
        Assert.assertEquals(1, NumIslands.numIslands(map));
    }

    @Test
    public void diagonalTest() {
        char[][] map = new char[][] {
            {'1', '1', '1', '1'},
            {'0', '1', '0', '1'},
            {'1', '1', '0', '0'},
            {'0', '0', '1', '0'},
            {'0', '0', '1', '1'}
        };
        Assert.assertEquals(2, NumIslands.numIslands(map));
    }

    @Test
    public void treeIslandsTest() {
        char[][] map = new char[][] {
            {'1', '1', '1', '1', '1'},
            {'0', '1', '0', '1', '0'},
            {'0', '0', '0', '0', '0'},
            {'1', '1', '1', '0', '0'},
            {'1', '0', '1', '1', '0'},
            {'1', '0', '0', '1', '1'},
            {'1', '0', '1', '0', '1'},
            {'1', '0', '0', '0', '1'},
            {'1', '1', '1', '1', '1'},
        };
        Assert.assertEquals(3, NumIslands.numIslands(map));
    }

    @Test
    public void noIslandsTest() {
        char[][] map = new char[][] {
            {'0', '0', '0', '0'},
            {'0', '0', '0', '0'},
            {'0', '0', '0', '0'},
            {'0', '0', '0', '0'},
            {'0', '0', '0', '0'},
            {'0', '0', '0', '0'},
        };
        Assert.assertEquals(0, NumIslands.numIslands(map));
    }

    @Test
    public void tempyTest() {
        char[][] map = new char[][] {{}};
        Assert.assertEquals(0, NumIslands.numIslands(map));
    }
}

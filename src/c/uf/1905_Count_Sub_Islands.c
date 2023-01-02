//
// Created by Mykhailo Petrenko on 02/01/2023.
//
/*
# 1905. Count Sub Islands (https://leetcode.com/problems/count-sub-islands/)

You are given two m x n binary matrices grid1 and grid2 containing only 0's (representing water)
 and 1's (representing land). An island is a group of 1's connected 4-directionally (horizontal or vertical).
Any cells outside of the grid are considered water cells.
An island in grid2 is considered a sub-island if there is an island in grid1 that contains all the cells that make up this island in grid2.
Return the number of islands in grid2 that are considered sub-islands.

Example 1:
    Input: grid1 = [
        [1,1,1,0,0],
        [0,1,1,1,1],
        [0,0,0,0,0],
        [1,0,0,0,0],
        [1,1,0,1,1]
    ],
    grid2 = [
        [1,1,1,0,0],
        [0,0,1,1,1],
        [0,1,0,0,0],
        [1,0,1,1,0],
        [0,1,0,1,0]
    ]
    Output: 3
    Explanation: In the picture above, the grid on the left is grid1 and the grid on the right is grid2.
    The 1s colored red in grid2 are those considered to be part of a sub-island. There are three sub-islands.

Example 2:
    Input: grid1 = [[1,0,1,0,1],[1,1,1,1,1],[0,0,0,0,0],[1,1,1,1,1],[1,0,1,0,1]], grid2 = [[0,0,0,0,0],[1,1,1,1,1],[0,1,0,1,0],[0,1,0,1,0],[1,0,0,0,1]]
    Output: 2
    Explanation: In the picture above, the grid on the left is grid1 and the grid on the right is grid2.
    The 1s colored red in grid2 are those considered to be part of a sub-island. There are two sub-islands.

Constraints:
    m == grid1.length == grid2.length
    n == grid1[i].length == grid2[i].length
    1 <= m, n <= 500
    grid1[i][j] and grid2[i][j] are either 0 or 1.
*/

#include <stdlib.h>
#include "../lib/utest.h"

void merge1(int *graph, int a, int b) {
    while(graph[a] != a) a = graph[a];
    while(graph[b] != b) b = graph[b];

    if (a > b) {
        graph[a] = b;
    } else {
        graph[b] = a;
    }
}

int countSubIslands(int** grid1, int grid1Size, int* grid1ColSize, int** grid2, int grid2Size, int* grid2ColSize) {
    int *graph1 = malloc(sizeof(int) * grid1Size * grid1Size);
    int current, sibling;

    for (int i = 0; i<grid1Size; i++) {
        for (int j = 0; j<grid1Size; j++) {
            current = i*grid1Size + j;

            if (grid1[i][j] == 0) {
                graph1[current] = -1;
                continue;
            }

            graph1[current] = current;

            // merge with up cell
            if (i > 0 && grid1[i-1][j]==1) {
                sibling = (i-1)*grid1Size + j;
                merge1(graph1, current, sibling);
            }

            // merge with left cell
            if (j > 0 && grid1[i][j-1]==1) {
                sibling = i*grid1Size + j - 1;
                merge1(graph1, current, sibling);
            }
        }
    }

    print_array(graph1, grid1Size * grid1Size);

    return 0;
}

void doTest(int grid1[5][5], int grid2[5][5], int gridSize, int expected) {
    int **g1 = malloc(sizeof(int *) * gridSize);
    int **g2 = malloc(sizeof(int *) * gridSize);

    for (int i=0; i<gridSize; i++) {
        g1[i] = malloc(sizeof(int) * gridSize);
        g2[i] = malloc(sizeof(int) * gridSize);

        for (int j=0; j<gridSize; j++) {
            g1[i][j] = grid1[i][j];
            g2[i][j] = grid2[i][j];
        }
    }

    int actual = countSubIslands(g1, gridSize, &gridSize, g2, gridSize, &gridSize);
    assert_equals_int("countSubIslands", &expected, &actual);
}

int main() {
    int grid1[5][5] = {{1,1,1,0,0},{0,1,1,1,1},{0,0,0,0,0},{1,0,0,0,0},{1,1,0,1,1}};
    int grid2[5][5] = {{1,1,1,0,0},{0,0,1,1,1},{0,1,0,0,0},{1,0,1,1,0},{0,1,0,1,0}};

    doTest(grid1, grid2, 5, 2);

    int grid3[5][5] = {{1,0,1,0,1},{1,1,1,1,1},{0,0,0,0,0},{1,1,1,1,1},{1,0,1,0,1}};
    int grid4[5][5] = {{0,0,0,0,0},{1,1,1,1,1},{0,1,0,1,0},{0,1,0,1,0},{1,0,0,0,1}};
    doTest(grid3, grid4, 5, 2);

    return EXIT_SUCCESS;
}

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

int * createGroups(int size) {
    int *g = malloc(sizeof(int) * size);

    for(int i=0; i<size; i++) {
        g[i] = i;
    }

    return g;
}

void unionGroups(int *g, int a, int b) {
    while(g[a] != a) a = g[a];
    while(g[b] != b) b = g[b];

    if (a > b) {
        g[a] = b;
    } else {
        g[b] = a;
    }
}

int getGroupId(int *g, int a) {
    while(g[a] != a) a = g[a];

    return a;
}

struct Group {
    int valid; // 0 - invalie, 1 - valid
    int parent;
    int id;
};

void unionGroups2(struct Group *g, int a, int b) {
    while(g[a].id != a) {
        g[g[a].id].parent = g[a].parent;
        a = g[a].id;
    }
    while(g[b].id != b) {
        g[g[b].id].parent = g[b].parent;
        b = g[b].id;
    }

    if (a > b) {
        g[a].id = b;
    } else {
        g[b].id = a;
    }

    if (g[b].valid == 0 || g[a].valid == 0) {
        g[a].valid = 0;
        g[b].valid = 0;
    }

    if (g[b].parent != g[a].parent) {
        g[a].valid = 0;
        g[b].valid = 0;
    }
}

int countValigGroups(struct Group *g, int size) {
    int count = 0;

    for(int i=0; i<size; i++) {
        if (g[i].id == i && g[i].valid == 1) {
            count++;
            printf("%d, %d, %d\n", g[i].id, g[i].parent, g[i].valid);
        }
    }

    return count;
}

struct Group * createGroups2(int size) {
    struct Group * g = malloc(sizeof(struct Group) * size);

    for (int i=0; i<size; i++) {
        g[i].valid = 0;
        g[i].parent = -1;
        g[i].id = -1;
    }

    return g;
}

int countSubIslands(int** grid1, int grid1Size, int* grid1ColSize, int** grid2, int grid2Size, int* grid2ColSize){
    int M = grid1Size;
    int N = grid1ColSize[0];

    int *groups = createGroups(M*N);

    for (int r=0; r<M; r++) {
        for(int c=0; c<N; c++) {
            if (grid1[r][c] == 0) continue;

            if (r > 0 && grid1[r-1][c] != 0) {
                unionGroups(groups, r*N + c, (r-1)*N + c);
            }

            if (c > 0 && grid1[r][c-1] != 0) {
                unionGroups(groups, r*N + c, r*N + c - 1);
            }
        }
    }

    struct Group *groups2 = createGroups2(M*N);
    int id;

    for (int r=0; r<grid2Size; r++) {
        for(int c=0; c<grid2ColSize[r]; c++) {
            if (grid2[r][c] == 0) continue;

            id = r*N + c;
            groups2[id].valid = (grid1[r][c] == 1) ? 1 : 0;
            groups2[id].parent = getGroupId(groups, id);
            groups2[id].id = id;

            if (r > 0 && grid2[r-1][c] != 0) {
                unionGroups2(groups2, r*N + c, (r-1)*N + c);
            }

            if (c > 0 && grid2[r][c-1] != 0) {
                unionGroups2(groups2, r*N + c, r*N + c - 1);
            }
        }
    }

    return countValigGroups(groups2, M*N);
}

void doTest(int grid1[5][5], int grid2[5][5], int gridSize, int expected) {
    int **g1 = malloc(sizeof(int *) * gridSize);
    int **g2 = malloc(sizeof(int *) * gridSize);
    int *gridColSize = malloc(sizeof(int)*gridSize);

    for (int i=0; i<gridSize; i++) {
        g1[i] = malloc(sizeof(int) * gridSize);
        g2[i] = malloc(sizeof(int) * gridSize);

        for (int j=0; j<gridSize; j++) {
            g1[i][j] = grid1[i][j];
            g2[i][j] = grid2[i][j];
        }

        gridColSize[i] = gridSize;
    }

    int actual = countSubIslands(g1, gridSize, gridColSize, g2, gridSize, gridColSize);
    assert_equals_int("countSubIslands", &expected, &actual);
}

int main() {
    int grid1[5][5] = {{1,1,1,0,0},{0,1,1,1,1},{0,0,0,0,0},{1,0,0,0,0},{1,1,0,1,1}};
    int grid2[5][5] = {{1,1,1,0,0},{0,0,1,1,1},{0,1,0,0,0},{1,0,1,1,0},{0,1,0,1,0}};

    doTest(grid1, grid2, 5, 3);

    int grid3[5][5] = {{1,0,1,0,1},{1,1,1,1,1},{0,0,0,0,0},{1,1,1,1,1},{1,0,1,0,1}};
    int grid4[5][5] = {{0,0,0,0,0},{1,1,1,1,1},{0,1,0,1,0},{0,1,0,1,0},{1,0,0,0,1}};
    doTest(grid3, grid4, 5, 2);

    return EXIT_SUCCESS;
}

//
// Created by Mykhailo Petrenko on 28/12/2022.
//
/*

# 959. Regions Cut By Slashes (https://leetcode.com/problems/regions-cut-by-slashes/)

An n x n grid is composed of 1 x 1 squares where each 1 x 1 square consists of a '/', '\', or blank space ' '.
These characters divide the square into contiguous regions.
Given the grid `grid` represented as a string array, return the number of regions.
Note that backslash characters are escaped, so a '\' is represented as '\\'.

## Example 1:
    Input: grid = [" /","/ "]
    Output: 2
## Example 2:
    Input: grid = [" /","  "]
    Output: 1
## Example 3:
    Input: grid = ["/\\","\\/"]
    Output: 5
    Explanation: Recall that because \ characters are escaped, "\\/" refers to \/, and "/\\" refers to /\.

## Constraints:
    n == grid.length == grid[i].length
    1 <= n <= 30
    grid[i][j] is either '/', '\', or ' '.
 * */
#include <stdlib.h>
#include "../lib/utest.h"


void groupId(int *graph, int *a) {
    while(graph[*a] != *a) {
        *a = graph[*a];
    }
}

void merge(int *graph, int a, int b) {
    groupId(graph, &a);
    groupId(graph, &b);

    if (a > b) {
        graph[a] = graph[b];
    } else {
        graph[b] = graph[a];
    }
}

/**
 *
 * Cell:
 *   0
 *   _
 * 3|_|1
 *   2
 *
 * @param grid
 * @param gridSize
 * @return
 */
int regionsBySlashes(char ** grid, int gridSize) {
    const int GROUPS_COUNT =  gridSize * gridSize * 4;
    int * graph = malloc(GROUPS_COUNT * sizeof(int));
    char s;
    int groupId;
    int siblingGroupId;

    // Init UF tree
    for (int i=0; i<GROUPS_COUNT; i++) {
        graph[i] = i;
    }

    // Visit each cell
    for (int y = 0; y < gridSize; y++) {
        for (int x = 0; x < gridSize; x++) {
            s = grid[y][x];
            groupId = ((y * gridSize) + x) * 4;

            // Divide each cell on virtual regions and merge then
            if (s == ' ') {
                merge(graph, groupId + 1, groupId + 0);
                merge(graph, groupId + 2, groupId + 0);
                merge(graph, groupId + 3, groupId + 0);
            } else if (s == '\\') {
                merge(graph, groupId + 1, groupId + 0);
                merge(graph, groupId + 3, groupId + 2);
            } else if (s == '/') {
                merge(graph, groupId + 3, groupId + 0);
                merge(graph, groupId + 2, groupId + 1);
            }

            // merge with top cell
            if (y > 0) {
                siblingGroupId = (((y - 1) * gridSize) + x) * 4;

                merge(graph, siblingGroupId + 2, groupId + 0);
            }

            // merge with left cell
            if (x > 0) {
                siblingGroupId = ((y * gridSize) + (x - 1)) * 4;

                merge(graph, siblingGroupId + 1, groupId + 3);
            }
        }
    }

    int groupsTotal = 0;

    for (int i=0; i<GROUPS_COUNT; i++) {
        if (graph[i] == i) groupsTotal++;
    }

    return groupsTotal;
}

void doTest(char ** grid, int gridSize, int expected) {
    int actual = regionsBySlashes(grid, gridSize);

    assert_equals_int("regionsBySlashes(char ** grid, int gridSize)", &expected, &actual);
}

int main() {
    char *grid1[] = {" /", "/ "};
    doTest(grid1, 2, 2);

    char *grid2[] = {" /","  "};
    doTest(grid2, 2, 1);

    char *grid3[] = {"/\\","\\/"};
    doTest(grid3, 2, 5);

    return 0;
}

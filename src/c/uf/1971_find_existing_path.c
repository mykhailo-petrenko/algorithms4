//
// Created by Mykhailo Petrenko on 19/12/2022.
//
#include <stdbool.h>

#include "../lib/utest.h"
/*
# 1971. Find if Path Exists in Graph [https://leetcode.com/problems/find-if-path-exists-in-graph/]

There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive).
The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] denotes a bi-directional edge between vertex ui and vertex vi.
Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.
You want to determine if there is a valid path that exists from vertex source to vertex destination.
Given edges and the integers n, source, and destination, return true if there is a valid path from source to destination, or false otherwise.

## Example 1:
    Input: n = 3, edges = [[0,1],[1,2],[2,0]], source = 0, destination = 2
    Output: true
    Explanation: There are two paths from vertex 0 to vertex 2:
    - 0 → 1 → 2
    - 0 → 2

## Example 2:
    Input: n = 6, edges = [[0,1],[0,2],[3,5],[5,4],[4,3]], source = 0, destination = 5
    Output: false
    Explanation: There is no path from vertex 0 to vertex 5.
 */

void clusterName(const int *graph, int *vertex) {
    while(graph[*vertex] != *vertex) {
        *vertex = graph[*vertex];
    }
}

void vertexUnion(int *graph, const int *edge) {
    int a = edge[0];
    int b = edge[1];

    clusterName(graph, &a);
    clusterName(graph, &b);

    if (graph[a] > graph[b]) {
        graph[a] = graph[b];
    } else {
        graph[b] = graph[a];
    }
}

bool validPath(int n, int** edges, int edgesSize, int* edgesColSize, int source, int destination) {
    int graph[n+1];
    int i;

    for (i = 0; i <= n; i++) {
        graph[i] = i;
    }

    for (i = 0; i < edgesSize; i++) {
        vertexUnion(graph, edges[i]);
    }

    clusterName(graph, &source);
    clusterName(graph, &destination);

    if (source == destination) {
        return true;
    } else {
        return false;
    }
}

void doTest(int n, int** edges, int edgesSize, int* edgesColSize, int source, int destination, bool expected) {

    // int n, int** edges, int edgesSize, int* edgesColSize, int source, int destination, bool expected
    bool actual = validPath(n, edges, edgesSize, edgesColSize, source, destination);

    assert_equals_bool("Is path exists", &expected, &actual);
}

int** createEdges(const int input[][2], const int edgesCount) {
    int **edges = malloc(sizeof(int *) * edgesCount);

    for (int i = 0; i < edgesCount; i++) {
        edges[i] = malloc(sizeof(int) * 2);
        edges[i][0] = input[i][0];
        edges[i][1] = input[i][1];
    }

    return edges;
}

void clearEdges(int **edges, const int edgesCount) {
    for (int i = 0; i < edgesCount; i++) {
        free(edges[i]);
    }

    free(edges);
}

int main() {
    int **edges;
    int colSize = 2;

    int input1[][2] = {{0,1},{1,2},{2,0}};
    edges = createEdges(input1, 3);

    doTest(3, edges, 3, &colSize, 0, 2, true);

    clearEdges(edges, 3);

    int input2[][2] = {{0,1},{0,2},{3,5},{5,4},{4,3}};
    edges = createEdges(input2, 5);

    doTest(6, edges, 5, &colSize, 0, 5, false);

    clearEdges(edges, 5);

    int input3[][2] = {{2,6},{4,7},{1,2},{3,5},{7,9},{6,4},{9,8},{0,1},{3,0}};
    edges = createEdges(input3, 9);

    doTest(10, edges, 9, &colSize, 3, 5, true);
    clearEdges(edges, 9);

    int input4[][2] = {{41,40},{9,32},{48,14},{6,44},{18,41},{41,1},{7,41},{38,41},{19,4},{16,41},{41,43},{41,22},{41,21},{9,0},{41,48},{32,36},{24,44},{39,41},{48,17},{49,15},{47,41},{19,31},{11,41},{41,23},{41,49},{45,44},{2,49},{13,41},{35,41},{30,0},{5,44},{8,0},{41,20},{41,28},{12,11},{12,41},{49,10},{19,0},{0,37},{34,41},{42,48},{27,41},{0,41},{19,44},{41,26},{41,29},{33,41},{49,46},{41,25},{3,41}};
    edges = createEdges(input4, 50);

    doTest(50, edges, 50, &colSize, 40, 3, true);
    clearEdges(edges, 9);

    return 0;
}
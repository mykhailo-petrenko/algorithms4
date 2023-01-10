//
// Created by Mykhailo Petrenko on 10.01.2023.
//
/*
# 1584. Min Cost to Connect All Points

You are given an array points representing integer coordinates of some points on a 2D-plane, where points[i] = [xi, yi].
The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between them: |xi - xj| + |yi - yj|, where |val| denotes the absolute value of val.
Return the minimum cost to make all points connected. All points are connected if there is exactly one simple path between any two points.

 */
#include <stdlib.h>
#include <stdio.h>

typedef struct {
    int i;
    int j;
    int weight;
} Edge;

typedef struct {
    int length;
    Edge * edges;
} Queue;

int distance(int **points, int i, int j) {
    return abs(points[i][0] - points[j][0]) + abs(points[i][1] - points[j][1]);
}

Queue * createQueue(int size);
void enqueue(Queue * q, Edge edge);
Edge * dequeue(Queue * q);

int minCostConnectPoints(int** points, int pointsSize, int* pointsColSize) {
    int **graph = malloc(sizeof(int *) * pointsSize);

    for (int i=0; i<pointsSize; i++) {
        graph[i] = malloc(sizeof(int) * pointsSize);
        graph[i][i] = 0;

        for (int j=i+1; j<pointsSize; j++) {
            graph[i][j] = distance(points, i, j);
            graph[j][i] = graph[i][j];
        }
    }
}

Queue * createQueue(int size) {
    Queue * q = malloc(sizeof(Queue));

    // 1 indexed array
    size++;
    q->edges = malloc(sizeof(Edge) * size);
    q->length = 0;

    return q;
}

void enqueue(Queue * q, Edge edge) {
    int index = q->length;
    int parent;
    q->length++;
    q->edges[index] = edge;
    Edge * buff = malloc(sizeof(Edge));

    while(index>1) {
        parent = index/2;

        if (q->edges[parent].weight <= q->edges[index].weight) {
            break;
        }

        *buff = q->edges[parent];
        q->edges[parent] = q->edges[index];
        q->edges[index] = *buff;

        index = parent;
    }

    free(buff);
}

Edge * dequeue(Queue * q) {
    int index, left, right;

}


int main() {
    return 0;
}
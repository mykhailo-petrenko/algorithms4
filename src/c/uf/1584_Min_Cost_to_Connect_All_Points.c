//
// Created by Mykhailo Petrenko on 10.01.2023.
//
/*
# 1584. Min Cost to Connect All Points (https://leetcode.com/problems/min-cost-to-connect-all-points/)

You are given an array points representing integer coordinates of some points on a 2D-plane, where points[i] = [xi, yi].
The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between them: |xi - xj| + |yi - yj|, where |val| denotes the absolute value of val.
Return the minimum cost to make all points connected. All points are connected if there is exactly one simple path between any two points.

 */
#include <stdlib.h>
#include <stdio.h>
#include "../lib/utest.h"

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
Edge dequeue(Queue * q);

int minCostConnectPoints(int** points, int pointsSize, int* pointsColSize) {
    int mstWeight = 0;
    int * connected = calloc(pointsSize, sizeof(int));
    int verticesLeft = pointsSize - 1;

    int EDGES_MAX = pointsSize * (pointsSize-1) / 2;
    Queue * q = createQueue(EDGES_MAX);


    connected[0] = 1;
    for (int i = 1; i<pointsSize; i++) {
        enqueue(q, (Edge){0, i, distance(points, 0, i)});
    }

    Edge e;

    while(verticesLeft > 0 && q->length > 0) {
        e = dequeue(q);

        if (connected[e.j] == 1) continue;

        mstWeight += e.weight;
        connected[e.j] = 1;
        verticesLeft--;

        for (int i = 0; i<pointsSize; i++) {
            if (connected[i] == 1) continue;
            if (i == e.j) continue;

            enqueue(q, (Edge){e.j, i, distance(points, i, e.j)});
        }
    }

    return mstWeight;
}

Queue * createQueue(int size) {
    Queue * q = malloc(sizeof(Queue));

    // 1 indexed array
    size++;
    q->edges = malloc(sizeof(Edge) * size);
    q->length = 0;

    return q;
}

void swap(Queue * q, int a, int b) {
    Edge tmp = q->edges[a];
    q->edges[a] = q->edges[b];
    q->edges[b] = tmp;
}

void enqueue(Queue * q, Edge edge) {
    int index;
    int parent;

    q->length++;
    index = q->length;
    q->edges[index] = edge;

    while(index>1) {
        parent = index/2;

        if (q->edges[parent].weight <= q->edges[index].weight) {
            break;
        }

        swap(q, index, parent);

        index = parent;
    }
}

Edge dequeue(Queue * q) {
    int index = 1;
    int left, right;
    Edge e = q->edges[1];

    q->edges[1] = q->edges[q->length];
    q->length--;

    while (true) {
        left = index * 2;
        right = index * 2 + 1;

        if (right<=q->length && q->edges[left].weight > q->edges[right].weight) {
            left++;
            right--;
        }

        if (left<=q->length && q->edges[index].weight > q->edges[left].weight) {
            swap(q, index, left);
            index = left;
            continue;
        }

        if (right<=q->length && q->edges[index].weight > q->edges[right].weight) {
            swap(q, index, right);
            index = right;
            continue;
        }

        break;
    }

    return e;
}

void doTest(int in[][2], int length, int expected) {
    int colSize = 2;

    int **points = malloc(sizeof(int *)* length);

    for (int i=0; i<length; i++) {
        points[i] = malloc(sizeof(int) * 2);
        points[i][0] = in[i][0];
        points[i][1] = in[i][1];
    }

    int actual = minCostConnectPoints(points, length, &colSize);

    assert_equals_int("minCostConnectPoints()", &expected, &actual);
}


int main() {
    doTest(
        (int [][2]){{-8,14},{16,-18},{-19,-13},{-18,19},{20,20},{13,-20},{-15,9},{-4,-8}},
        8,
        139
        );

    doTest(
        (int [][2]){{0,0},{2,2},{3,10},{5,2},{7,0}},
        5,
        20
        );

    doTest(
            (int [][2]){{0,0},{1,1},{1,0},{-1,1}},
            4,
            4
    );

    doTest(
            (int[][2]){{3,12},{-2,5},{-4,1}},
            3,
            18
            );

    doTest(
            (int[][2]){{0, 0}},
            1,
            0
    );

    return 0;
}
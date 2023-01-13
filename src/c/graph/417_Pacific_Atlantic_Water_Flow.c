//
// Created by Mykhailo Petrenko on 13/01/2023.
//

/*
# 417. Pacific Atlantic Water Flow (https://leetcode.com/problems/pacific-atlantic-water-flow/)

There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean.
The Pacific Ocean touches the island's left and top edges,
 and the Atlantic Ocean touches the island's right and bottom edges.

The island is partitioned into a grid of square cells.
You are given an m x n integer matrix heights where heights[r][c] represents the height above sea level of the cell at coordinate (r, c).

The island receives a lot of rain, and the rain water can flow to neighboring cells directly north,
 south, east, and west if the neighboring cell's height is less than or equal to the current cell's height. Water can flow from any cell adjacent to an ocean into the ocean.

Return a 2D list of grid coordinates result where result[i] = [ri, ci]
 denotes that rain water can flow from cell (ri, ci) to both the Pacific and Atlantic oceans.


## Constraints:
    m == heights.length
    n == heights[r].length
    1 <= m, n <= 200
    0 <= heights[r][c] <= 10^5

 */
#include <stdlib.h>
#include <stdio.h>
#include <limits.h>
#include "utest.h"

struct Queue {
    int capacity;
    int start;
    int count;
    int * queue;
};

struct Queue * queue_create(int initialCapacity);
void queue_resize(struct Queue * q, int factor);
void enqueue(struct Queue * q, int el);
int dequeue(struct Queue * q);
int read_queue(struct Queue * q, int i);

void flowCalc(char * p, int ** heights, int M, int N, struct Queue * q) {
    int r, c, node;

    while(q->count > 0) {
        node = dequeue(q);

        r = node / N;
        c = node % N;

        if (r > 0 && heights[r-1][c] >= heights[r][c] && p[node-N] == 0) {
            p[node-N] = 1;
            enqueue(q, node-N);
        }
        if (c > 0 && heights[r][c-1] >= heights[r][c] && p[node-1] == 0) {
            p[node-1] = 1;
            enqueue(q, node-1);
        }
        if (r < M-1 && heights[r+1][c] >= heights[r][c] && p[node+N] == 0) {
            p[node+N] = 1;
            enqueue(q, node+N);
        }
        if (c < N-1 && heights[r][c+1] >= heights[r][c] && p[node+1] == 0) {
            p[node+1] = 1;
            enqueue(q, node+1);
        }
    }
}

/**
 * Return an array of arrays of size *returnSize.
 * The sizes of the arrays are returned as *returnColumnSizes array.
 * Note: Both returned array and *columnSizes array must be malloced, assume caller calls free().
 */
int** pacificAtlantic(int** heights, int heightsSize, int* heightsColSize, int* returnSize, int** returnColumnSizes){
    const int M = heightsSize;
    const int N = heightsColSize[0];
    const int NODES = M*N;

    // pacific <--
    char * p = calloc(NODES, sizeof(char));
    // atlantic -->
    char * a = calloc(NODES, sizeof(char));

    int r, c, node;

    struct Queue * q = queue_create(M);
    int *visited = calloc(M*N, sizeof(short));

    r = 0;
    for (c=0; c<N; c++) {
        node = r*N + c;
        p[node] = 1;
        enqueue(q, node);
    }

    c = 0;
    for (r=1; r<M; r++) {
        node = r*N + c;
        p[node] = 1;
        enqueue(q, node);
    }

    flowCalc(p, heights, M, N, q);

    free(q->queue);
    free(q);
    q = queue_create(M);

    r = M-1;
    for (c=0; c<N-1; c++) {
        node = r*N + c;
        a[node] = 1;
        enqueue(q, node);
    }

    c = N-1;
    for (r=0; r<M; r++) {
        node = r*N + c;
        a[node] = 1;
        enqueue(q, node);
    }

    flowCalc(a, heights, M, N, q);

    free(q->queue);
    free(q);
    q = queue_create(M);

    for (node=0; node < NODES; node++) {
        if (p[node] == 1 && a[node] == 1) {
            enqueue(q, node);
        }
    }

    int **out = malloc(sizeof(int *) * q->count);
    *returnColumnSizes = malloc(sizeof(int) * q->count);

    *returnSize = q->count;

    int i = 0;
    while(q->count > 0) {
        node = dequeue(q);
        r = node / N;
        c = node % N;

        out[i] = malloc(sizeof(int) * 2);
        out[i][0] = r;
        out[i][1] = c;
        (*returnColumnSizes)[i] = 2;

        i++;
    }

    return out;
}

struct Queue * queue_create(int initialCapacity) {
    struct Queue * q = malloc(sizeof(struct Queue));

    q->capacity = initialCapacity;
    q->start = 0;
    q->count = 0;
    q->queue = malloc(sizeof(int) * initialCapacity);

    return q;
}

void queue_resize(struct Queue * q, int factor) {
    int * prev_queue = q->queue;

    q->queue = malloc(sizeof(struct Queue) * q->capacity * factor);

    for (int i=0; i<q->count; i++) {
        q->queue[i] = prev_queue[(q->start + i) % q->capacity];
    }

    q->capacity *= factor;
    q->start = 0;
}

void enqueue(struct Queue * q, int el) {
    if (q->capacity == q->count + 1) {
        queue_resize(q, 2);
    }

    int index = (q->start + q->count) % q->capacity;

    q->queue[index] = el;
    q->count++;
}

int dequeue(struct Queue * q) {
    int start = q->start;

    q->count--;
    q->start = (q->start + 1) % q->capacity;

    return q->queue[start];
}

int read_queue(struct Queue * q, int i) {
    return q->queue[(q->start + i) % q->capacity];
}

void doTest(const ArrayPointer *p) {
    printf("> Input:\n");
    print_2d_array(p->arr, p->rowsSize, p->columnsSize);
    int outSize;
    int **outColSize;


    int **out = pacificAtlantic(p->arr, p->rowsSize, p->columnsSize, &outSize, outColSize);

    printf("\nOutput: %d", outSize);
    print_2d_array(out, outSize, *outColSize);
}

int main() {
    ArrayPointer * p;

    int in1[][5] = {{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}};
    p = arrayToPointer(&in1[0][0], 5, 5);

    doTest(p);

    int in2[][1] = {{1}};
    p = arrayToPointer(&in2[0][0], 1, 1);

    doTest(p);

    return 0;
}
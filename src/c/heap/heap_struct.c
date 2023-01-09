//
// Created by Mykhailo Petrenko on 09/01/2023.
//

#include <stdlib.h>
#include <stdio.h>
#include <limits.h>

typedef int (* Comparator)(const void * data, const int * a, const int * b);

struct Heap {
    int capacity;
    int length;
    int * array;
    void * data;
    Comparator compare;
};

typedef struct Heap Heap;

Heap * heap_create(int capacity, Comparator compare) {
    Heap * h = malloc(sizeof(Heap));
    h->capacity = capacity;
    h->length = 0;
    h->array = (int *)malloc(sizeof(int) * (capacity + 1));

    for (int i=1; i<=capacity; i++) h->array[i] = INT_MAX;

    return h;
}

void swap(int *a, int *b) {
    int tmp = *a;
    *a = *b;
    *b = tmp;
}

void heap_insert(Heap * heap, int node_id) {
    int index = heap->length;
    heap->array[index+1] = node_id;
    int parent;

    while(index > 1) {
        parent = index / 2;

        if (heap->compare(heap->data, &parent, &index)) {
            swap(&heap->array[parent], &heap->array[index]);

            index = parent;
        } else {
            break;
        }
    }

    heap->length++;
}

struct Node {
    int a;
    int b;
    int weight;
};

typedef struct Node Node;

int node_compare(const void * data, const int *a, const int *b) {
    Node * n = (Node *)data;

    return n[*a].weight - n[*b].weight;
}

void node_create(Node * node, int a, int b, int weight) {
    node->weight = weight;
    node->a = a;
    node->b = b;
}

void heap_print(Heap * heap);

int main() {
    Heap * h = heap_create(10, node_compare);

    h->data = (Node *)malloc(sizeof(Node) * 10);;

    node_create(&h->data[0], 1,2,35);
    node_create(&h->data[1], 1,3,17);
    node_create(&h->data[2], 1,4,5);
    node_create(&h->data[3], 2,3,8);



    heap_print(h);

    heap_insert(h, 0);
    heap_print(h);

    heap_insert(h, 1);
    heap_print(h);

    heap_insert(h, 2);
    heap_print(h);

    heap_insert(h, 3);
    heap_print(h);

    return 0;
}

void heap_print(Heap * heap) {
    printf("[");
    for (int i=1; i<=heap->length; i++) {
        printf("%d, ",heap->array[i]);
    }
    printf("]\n");
}
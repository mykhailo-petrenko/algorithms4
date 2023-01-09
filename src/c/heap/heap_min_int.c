//
// Created by Mykhailo Petrenko on 09/01/2023.
//

#include <stdlib.h>
#include <limits.h>
#include <stdio.h>


/**
 * Min Heap only for integer values
 */
struct Heap {
    int capacity;
    int length;
    int * heap;
};

typedef struct Heap Heap;

/**
 * Create "1"-indexed heap
 * @param capacity
 * @return
 */
Heap * heap_create(int capacity) {
    Heap * h = malloc(sizeof(Heap));

    h->heap = (int *)malloc(sizeof(int) * (capacity + 1));
    h->capacity = capacity;
    h->length = 0;

    for (int i=1; i<=capacity; i++) h->heap[i] = INT_MAX;

    return h;
}

void swap(int *a, int *b) {
    int tmp = *a;
    *a = *b;
    *b = tmp;
}

void heap_insert(Heap * heap, int node) {
    int index = heap->length;
    heap->heap[index+1] = node;
    int parent;

    while(index > 1) {
        parent = index / 2;

        if (heap->heap[parent] > heap->heap[index]) {
            swap(&heap->heap[parent], &heap->heap[index]);

            index = parent;
        } else {
            break;
        }
    }

    heap->length++;
}

int heap_extract(Heap * heap) {
    int out = heap->heap[1];

    heap->heap[1] = heap->heap[heap->length];
    heap->heap[heap->length] = INT_MAX;
    heap->length--;

    int index = 1;
    int next = 1;
    int left, right;
    while(index <= heap->length) {
        left = index*2;
        right = index*2 + 1;

        if (heap->heap[left] > heap->heap[right]) {
            right--;
            left++;
        }

        if (heap->heap[index] > heap->heap[left]) {
            next = left;
        } else if (heap->heap[index] > heap->heap[right]) {
            next = right;
        } else {
            break;
        }

        swap(&heap->heap[index], &heap->heap[next]);
    }

    return out;
}

void heap_print(Heap * heap);

int main() {
    Heap * heap = heap_create(10);

    heap_print(heap);

    heap_insert(heap, 10);
    heap_print(heap);

    heap_insert(heap, 5);
    heap_print(heap);

    heap_insert(heap, 3);
    heap_print(heap);

    heap_insert(heap, 7);
    heap_print(heap);

    heap_insert(heap, 1);
    heap_print(heap);

    heap_insert(heap, 8);
    heap_print(heap);

    heap_insert(heap, 2);
    heap_print(heap);

    heap_insert(heap, 8);
    heap_print(heap);

    printf("\n == Extract:\n");

    while(heap->length > 0) {
        printf("> %d\n", heap_extract(heap));
        heap_print(heap);
    }

    return 0;
}


void heap_print(Heap * heap) {
    printf("[");
    for (int i=1; i<=heap->length; i++) {
        printf("%d, ",heap->heap[i]);
    }
    printf("]\n");
}
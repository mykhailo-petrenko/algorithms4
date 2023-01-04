//
// Created by Mykhailo Petrenko on 04.01.2023.
//

#include <stdlib.h>
#include <stdio.h>

struct Queue {
    int capacity;
    int start;
    int count;
    int * queue;
};

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

int main() {
    int b;
    struct Queue * q = queue_create(4);

    printf(" = Resizable Queue with cyclic buffer\n");

    enqueue(q, 1);
    enqueue(q, 2);
    enqueue(q, 3);
    //enqueue(q, 4);

    printf("Init [1, 2, 3]. Count: %d, Capacity: %d \n", q->count, q->capacity);

    printf("Deq %d. Count: %d, Capacity: %d \n", dequeue(q), q->count, q->capacity);

    enqueue(q, 11);
    enqueue(q, 12);
    enqueue(q, 13);

    printf("Enq [11, 12, 13]. Count: %d, Capacity: %d \n", q->count, q->capacity);


    printf("Deq %d. Count: %d, Capacity: %d \n", dequeue(q), q->count, q->capacity);
    printf("Deq %d. Count: %d, Capacity: %d \n", dequeue(q), q->count, q->capacity);
    printf("Deq %d. Count: %d, Capacity: %d \n", dequeue(q), q->count, q->capacity);
    printf("Deq %d. Count: %d, Capacity: %d \n", dequeue(q), q->count, q->capacity);


    return 0;
}
//
// Created by Mykhailo Petrenko on 09/03/2026.
//

#include <stdlib.h>
#include <stdio.h>

struct Queue {
    int capacity;
    int size;
    int front;
    int * queue;
};

struct Queue * queue_create(int capacity);
int queue_front(const struct Queue * q);
void queue_pop(struct Queue * q);
void queue_push(struct Queue * q, int element);
void queue_destroy(struct Queue * q);
void queue_resize(struct Queue * q, int capacity);

struct Queue * queue_create(const int capacity) {
    struct Queue * q = malloc(sizeof(struct Queue));
    q->capacity = capacity;
    q->size = 0;
    q->front = 0;
    q->queue = malloc(capacity * sizeof(int));
    return q;
}

void queue_destroy(struct Queue * q) {
    free(q->queue);
    free(q);
}

int queue_front(const struct Queue * q) {
    if (q == NULL || q->queue == NULL) {
        return -1;
    }

    return q->queue[q->front];
}

void queue_pop(struct Queue * q) {
    if (q == NULL) {
        return;
    }

    if (q->size == 0) {
        return;
    }

    q->front = (q->front + 1) % q->capacity;
    q->size--;
}

void queue_push(struct Queue * q, const int element) {
    if (q == NULL) {
        return;
    }

    if (q->size == q->capacity) {
        queue_resize(q, q->capacity * 2);
    }

    const int next_index = (q->front + q->size) % q->capacity;

    q->queue[next_index] = element;
    q->size++;
}

void queue_resize(struct Queue * q, const int capacity) {
    if (q == NULL) {
        return;
    }

    if (capacity < q->size) {
        return;
    }

    int * new_queue = malloc(capacity * sizeof(int));

    for (int i=0, front=q->front; i<q->size; i++, front = (front + 1) % q->capacity) {
        new_queue[i] = q->queue[front];
    }

    free(q->queue);
    q->queue = new_queue;
    q->front = 0;
    q->capacity = capacity;
}

int main() {
    int buffer;
    struct Queue * q = queue_create(5);
    printf(" = Resizable Queue with cyclic buffer\n");

    queue_push(q, 1);
    queue_push(q, 2);
    queue_push(q, 3);

    printf("Init [1, 2, 3]. Count: %d, Capacity: %d \n", q->size, q->capacity);

    buffer = queue_front(q);
    queue_pop(q);
    printf("queue_pop %d. Count: %d, Capacity: %d \n", buffer, q->size, q->capacity);

    queue_push(q, 11);
    printf("queue_push [11]. Count: %d, Capacity: %d \n", q->size, q->capacity);

    queue_push(q, 12);
    printf("queue_push [12]. Count: %d, Capacity: %d \n", q->size, q->capacity);

    queue_push(q, 13);
    printf("queue_push [13]. Count: %d, Capacity: %d \n", q->size, q->capacity);

    buffer = queue_front(q);
    queue_pop(q);
    printf("queue_pop %d. Count: %d, Capacity: %d \n", buffer, q->size, q->capacity);

    buffer = queue_front(q);
    queue_pop(q);
    printf("queue_pop %d. Count: %d, Capacity: %d \n", buffer, q->size, q->capacity);

    buffer = queue_front(q);
    queue_pop(q);
    printf("queue_pop %d. Count: %d, Capacity: %d \n", buffer, q->size, q->capacity);

    buffer = queue_front(q);
    queue_pop(q);
    printf("queue_pop %d. Count: %d, Capacity: %d \n", buffer, q->size, q->capacity);

    queue_push(q, 14);
    printf("queue_push [14]. Count: %d, Capacity: %d \n", q->size, q->capacity);

    queue_push(q, 15);
    printf("queue_push [15]. Count: %d, Capacity: %d \n", q->size, q->capacity);

    queue_push(q, 16);
    printf("queue_push [16]. Count: %d, Capacity: %d \n", q->size, q->capacity);

    const int N = q->capacity * 10;

    for (int i=0; i < N; i++) {
        queue_push(q, i);
        printf("queue_push [%d]. Count: %d, Capacity: %d, Front %d \n", i, q->size, q->capacity, q->front);

        queue_pop(q);
        printf("queue_pop: Count: %d, Capacity: %d, Front %d \n", q->size, q->capacity, q->front);
    }

    for (int i=0; i < N; i++) {
        queue_push(q, i);
        printf("queue_push [%d]. Count: %d, Capacity: %d, Front %d, Value: %d \n", i, q->size, q->capacity, q->front, queue_front(q));
    }

    while (q->size > 0) {
        printf("queue_pop. Count: %d, Capacity: %d, Front %d, Value: %d \n", q->size, q->capacity, q->front, queue_front(q));
        queue_pop(q);
    }

    queue_destroy(q);

    return 0;
}

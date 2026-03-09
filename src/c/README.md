# Algorithms and coding problems C practice playground

## Utils and Helpers (to DRY)

### Queue 

[Implementation queue/queue.c](queue/queue.c)

Usage:
```c
struct Queue * q =  queue_create(INITIAL_CAPACITY);

// Push (add element to the Queue)
queue_push(q, new_element);

// Get the front element
int front_value = queue_front(q);
queue_pop(q);

// Free memory
queue_destroy(q);
```


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

### UF

[Implementation UF](uf/uf.c)

Usage:
```c
struct UF * uf =  uf_create(INITIAL_CAPACITY);

// Add connection
uf_add(uf, a, b);

// Check if the nodes are connected (1 - connected, 0 - not connected)
uf_connected(uf, a, b)
```

# JS 

## LeetCode problems boilerplate generator.

Generates js file for function and for unit test.
The only one parameter - problem name with id. For example:
`yarn leetcode "473. Matchsticks to Square"`

```shell
yarn leetcode ""
```

## Data Structures interfaces

### Priority Queue

On the LeetCode environment available `"@datastructures-js/priority-queue": "5.4.0",`. Interface of v.5 (NOT 6).

```js
import { PriorityQueue } from '@datastructures-js/priority-queue';

// Min priority queue
const q = new PriorityQueue({
  compare: (a, b) => {
    return a - b;
  }
});

q.enqueue(5);
q.dequeue().element; // get the object
q.isEmpty();
q.size();
q.clear();
```


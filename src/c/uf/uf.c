#include <stdio.h>
#include <stdlib.h>
#include <assert.h>

struct UF
{
  int *graph;
  int *ranks;
  int capacity;
  int groups;
};

void uf_add(struct UF * uf, const int a, const int b);
int uf_connected(struct UF * uf, const int a, const int b);
int uf_find(struct UF * uf, const int a);

struct UF *uf_create(const int capacity)
{
  struct UF *uf = malloc(sizeof(struct UF));
  uf->graph = malloc(sizeof(int) * capacity);
  uf->ranks = malloc(sizeof(int) * capacity);
  uf->capacity = capacity;
  uf->groups = capacity;

  for (int i = 0; i < capacity; i++)
  {
    uf->graph[i] = i;
    uf->ranks[i] = 1;
  }

  return uf;
}

void uf_add(struct UF * uf, int a, int b) {
  a = uf_find(uf, a);
  b = uf_find(uf, b);

  if (a == b) {
    return;
  }

  if (uf->ranks[a] < uf->ranks[b]) {
    uf->graph[a] = b;
    uf->ranks[b] += uf->ranks[a];
  } else {
    uf->graph[b] = a;
    uf->ranks[a] += uf->ranks[b];
  }

  uf->groups--;
}

int uf_connected(struct UF * uf, const int a, const int b) {
  if (uf_find(uf, a) == uf_find(uf, b)) {
    return 1;
  }

  return 0;
}

int uf_find(struct UF * uf, int a) {
  while (a != uf->graph[a]) {
    a = uf->graph[a];
  }

  return a;
}

void uf_smoke_test()
{
  struct UF * uf = uf_create(10);

  uf_add(uf,4, 3);
  uf_add(uf,3, 8);
  uf_add(uf,6, 5);
  uf_add(uf,9, 4);
  uf_add(uf,2, 1);

  assert(uf_connected(uf, 8, 9));

  uf_add(uf,5, 0);
  uf_add(uf,7, 2);
  uf_add(uf,6, 1);

  assert(uf_connected(uf,1, 0));
  assert(uf_connected(uf,6, 7));
  assert(uf_connected(uf,1, 7));
  assert(uf_connected(uf,8, 4));

  assert(!uf_connected(uf,0, 9));
  assert(!uf_connected(uf,7, 3));
  assert(!uf_connected(uf,7, 8));
  assert(!uf_connected(uf,2, 3));
}

int main()
{
  printf("UF test:\n");
  uf_smoke_test();
  printf("uf_smoke_test passed!\n");
  return 0;
}

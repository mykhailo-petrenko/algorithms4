//
// Created by Mykhailo Petrenko on 04/10/2023.
//
/*
# 706. Design HashMap

Design a HashMap without using any built-in hash table libraries.

Implement the MyHashMap class:
    MyHashMap() initializes the object with an empty map.
    void put(int key, int value) inserts a (key, value) pair into the HashMap. If the key already exists in the map, update the corresponding value.
    int get(int key) returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
    void remove(key) removes the key and its corresponding value if the map contains the mapping for the key.

## Example 1:

Input
["MyHashMap", "put", "put", "get", "get", "put", "get", "remove", "get"]
[[], [1, 1], [2, 2], [1], [3], [2, 1], [2], [2], [2]]
Output
[null, null, null, 1, -1, null, 1, null, -1]

### Explanation
    MyHashMap myHashMap = new MyHashMap();
    myHashMap.put(1, 1); // The map is now [[1,1]]
    myHashMap.put(2, 2); // The map is now [[1,1], [2,2]]
    myHashMap.get(1);    // return 1, The map is now [[1,1], [2,2]]
    myHashMap.get(3);    // return -1 (i.e., not found), The map is now [[1,1], [2,2]]
    myHashMap.put(2, 1); // The map is now [[1,1], [2,1]] (i.e., update the existing value)
    myHashMap.get(2);    // return 1, The map is now [[1,1], [2,1]]
    myHashMap.remove(2); // remove the mapping for 2, The map is now [[1,1]]
    myHashMap.get(2);    // return -1 (i.e., not found), The map is now [[1,1]]


### Constraints:
    0 <= key, value <= 106
    At most 104 calls will be made to put, get, and remove.

 **/

#include <stdlib.h>
#include <stdio.h>
#include <assert.h>
#include "utest.h"



// default values recommended by http://isthe.com/chongo/tech/comp/fnv/
#define PRIME 0x01000193
#define SEED 0x811C9DC5

uint32_t fnv1a(const void* data, size_t numBytes)
{
    assert(data);
    uint32_t hash = SEED;
    const unsigned char* ptr = (const unsigned char*)data;
    while (numBytes--) {
        hash = ((*ptr++) ^ hash) * PRIME;
    }
    return hash;
}


typedef struct Node Node;

struct Node {
    int key;
    int value;
    Node * next;
};

typedef struct {
    Node * nodes;
    int capacity;
    int size;
} MyHashMap;


MyHashMap* myHashMapCreate() {
    const int INITIAL_CAPACITY = 2;
    MyHashMap *hashMap = malloc(sizeof(MyHashMap));
    hashMap->capacity = INITIAL_CAPACITY;
    hashMap->size = 0;
    hashMap->nodes = (Node *)malloc(sizeof(Node) * INITIAL_CAPACITY);

    for (int i=0; i<hashMap->capacity;i++) {
        hashMap->nodes[i].key = -1;
        hashMap->nodes[i].value = 0;
        hashMap->nodes[i].next = NULL;
    }

    return hashMap;
}
void resizeHashMap(MyHashMap* obj, int newCapacity);
void insertHashMap(MyHashMap* obj, int key, int value);

void myHashMapPut(MyHashMap* obj, int key, int value) {
    if (obj->size > (obj->capacity / 2)) {
        resizeHashMap(obj, obj->capacity * 2);
    }

    insertHashMap(obj, key, value);
}

int myHashMapGet(MyHashMap* obj, int key) {
    unsigned int hash = fnv1a(&key, sizeof(int));
    unsigned int i = hash % obj->capacity;

    Node * head = &obj->nodes[i];

    while (head != NULL) {
        if (head->key == key) {
            return head->value;
        }

        head = head->next;
    }

    return -1;
}

void myHashMapRemove(MyHashMap* obj, int key) {
    unsigned int hash = fnv1a(&key, sizeof(int));
    unsigned int i = hash % obj->capacity;

    if (obj->nodes[i].key == -1) {
        return;
    }

    if (obj->nodes[i].key == key) {
        if (obj->nodes[i].next == NULL) {
            obj->nodes[i].key = -1;
        } else {
            obj->nodes[i] = *obj->nodes[i].next;
        }
        obj->size--;
        return;
    }

    Node * head = &obj->nodes[i];

    while (head->next != NULL) {
        if (head->next->key == key) {
            head->next = head->next->next;
            obj->size--;
            return;
        }

        head = head->next;
    }
}

void myHashMapFree(MyHashMap* obj) {
    obj->size = 0;
    Node * node;
    for (int i=0; i < obj->capacity; i++) {
        node = &obj->nodes[i];
        free(node->next);
        node->key = -1;
    }
}

void resizeHashMap(MyHashMap* obj, int newCapacity) {
    Node * oldNodes = obj->nodes;
    int capacity = obj->capacity;

    obj->nodes = malloc(sizeof(Node) * newCapacity);
    obj->capacity = newCapacity;
    obj->size = 0;
    for (int i=0; i<obj->capacity; i++) {
        obj->nodes[i].key = -1;
        obj->nodes[i].value = 0;
        obj->nodes[i].next = NULL;
    }

    for (int i=0; i<capacity; i++) {
        if (oldNodes[i].key == -1) {
            continue;
        }

        Node * node = &oldNodes[i];
        while (node != NULL) {
            insertHashMap(obj, node->key, node->value);
            node = node->next;
        }
    }
}

void insertHashMap(MyHashMap* obj, int key, int value) {
    unsigned int hash = fnv1a(&key, sizeof(int));
    unsigned int i = hash % obj->capacity;

    if (obj->nodes[i].key == -1) {
        obj->nodes[i].key = key;
        obj->nodes[i].value = value;
    } else {
        Node * head = &obj->nodes[i];
        while (head->next != NULL) {
            if (head->key == key) {
                head->value = value;
                return;
            }

            head = head->next;
        }

        if (head->key == key) {
            head->value = value;
            return;
        }

        head->next = malloc(sizeof(Node));
        head->next->key = key;
        head->next->value = value;
        head->next->next = NULL;
    }

    obj->size++;
}

/**
 * Your MyHashMap struct will be instantiated and called as such:
 * MyHashMap* obj = myHashMapCreate();
 * myHashMapPut(obj, key, value);

 * int param_2 = myHashMapGet(obj, key);

 * myHashMapRemove(obj, key);

 * myHashMapFree(obj);
*/

int main() {
    /*
Input
["MyHashMap", "put", "put", "get", "get", "put", "get", "remove", "get"]
[[], [1, 1], [2, 2], [1], [3], [2, 1], [2], [2], [2]]
Output
[null, null, null, 1, -1, null, 1, null, -1]
     */
    int out, expect;

    MyHashMap * map = myHashMapCreate();
    myHashMapPut(map, 1, 1);
    expect = 1;
    assert_equals_int("map->size", &expect, &map->size);
    myHashMapPut(map, 2, 2);
    expect = 2;
    assert_equals_int("map->size", &expect, &map->size);

    out = myHashMapGet(map, 1);
    expect = 1;
    assert_equals_int("myHashMapGet(map, 1);", &expect, &out);

    out = myHashMapGet(map, 3);
    expect = -1;
    assert_equals_int("myHashMapGet(map, 3);", &expect, &out);

    myHashMapPut(map, 2, 1);
    out = myHashMapGet(map, 2);
    expect = 1;
    assert_equals_int("myHashMapGet(map, 2);", &expect, &out);
    expect = 2;
    assert_equals_int("map->size", &expect, &map->size);

    myHashMapRemove(map, 2);
    myHashMapRemove(map, 5);

    expect = 1;
    assert_equals_int("map->size", &expect, &map->size);

    out = myHashMapGet(map, 2);
    expect = -1;
    assert_equals_int("myHashMapGet(map, 2);", &expect, &out);

    myHashMapFree(map);

    out = myHashMapGet(map, 1);
    expect = -1;
    assert_equals_int("myHashMapGet(map, 1);", &expect, &out);

    expect = 0;
    assert_equals_int("should be empty", &expect, &map->size);

    return 0;
}
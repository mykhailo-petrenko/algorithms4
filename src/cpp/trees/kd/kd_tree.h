//
// Created by Mykhailo Petrenko on 24/03/2022.
//
// https://www.geeksforgeeks.org/k-dimensional-tree/

#ifndef ALGORITHMS4_KD_TREE_H
#define ALGORITHMS4_KD_TREE_H

#define K 2

struct Node {
    int point[2];
    Node *left;
    Node *right;
};

struct Node* newNode(int point[]) {
    struct Node* tmp = new Node;

    for (int i = 0; i < K; i++) {
        tmp->point[i] = point[i];
    }

    tmp->left = nullptr;
    tmp->right = nullptr;

    return tmp;
}

#endif //ALGORITHMS4_KD_TREE_H

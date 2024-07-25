#include <iostream>

struct Node {
    int val;
    Node * next;
};

int main() {

    std::cout << "Hello CPP";

    Node * root = new Node;

    root->val = 17;

    std::cout << root->val << " -- " << root->next << " ||";


    std::cin.get();

    free(root);

    return 0;
}
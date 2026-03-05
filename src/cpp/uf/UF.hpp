//
// Created by Mykhailo Petrenko on 05/03/2026.
//

#ifndef ALGORITHMS4_UF_HPP
#define ALGORITHMS4_UF_HPP


class UF {
private:
    int * graph;
    int * rank;
    int N; // capacity

    void optimize(const int root, int child) {
        while (graph[child] != root) {
            const int oldChild = graph[child];
            graph[child] = root;
            child = oldChild;
        }
    }
public:
    ~UF() {
        delete[] graph;
        delete[] rank;
    }
    UF(const int size) {
        N = size;
        graph = new int[N];
        rank = new int[N];
        for (int i = 0; i < N; i++) {
            graph[i] = i;
            rank[i] = 1;
        }
    }

    int find(int id) {
        while (id != graph[id]) {
            id = graph[id];
        }

        return id;
    }

    bool connected(const int a, const int b) {
        return (find(a) == find(b));
    }

    void add(const int a, const int b) {
        const int rootA = find(a);
        const int rootB = find(b);

        if (rootA != a) {
            optimize(rootA, a);
        }

        if (rootB != b) {
            optimize(rootB, b);
        }

        if (rootA == rootB) {
            return;
        }

        if (rank[rootA] < rank[rootB]) {
            graph[rootA] = rootB;
            rank[rootB] += rank[rootA];
        } else {
            graph[rootB] = rootA;
            rank[rootA] += rank[rootB];
        }
    }


};


#endif //ALGORITHMS4_UF_HPP
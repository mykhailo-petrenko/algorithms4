package uf;

public class BalancedQuickUnionUF implements UF {
    private int count;
    private int[] data;
    private int[] size;

    public BalancedQuickUnionUF(int N) {
        this.count = N;
        this.data = new int[N];
        this.size = new int[N];

        for (int i = 0; i < N; i++) {
            data[i] = i;
            size[i] = 1;
        }
    }

    @Override
    public void union(int p, int q) {
        int rootP = find(p);
        int sizeP = size[rootP];

        int rootQ = find(q);
        int sizeQ = size[rootQ];

        int newSize = sizeP + sizeQ;

        if (sizeP > sizeQ) {
            data[rootQ] = rootP;
            size[rootP] = newSize;
        } else {
            data[rootP] = rootQ;
            size[rootQ] = newSize;
        }

        count--;
    }

    @Override
    public int find(int p) {
        while (p != data[p]) {
            p = data[p];
        }

        return p;
    }

    @Override
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public int count() {
        return count;
    }
}

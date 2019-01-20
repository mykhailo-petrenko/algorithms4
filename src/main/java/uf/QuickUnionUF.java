package uf;

public class QuickUnionUF implements UF {
    private int N;
    private int count;
    private int[] data;


    public QuickUnionUF(int N) {
        this.N = N;
        this.count = N;
        this.data = new int[N];

        for (int i = 0; i < N; i++) {
            data[i] = i;
        }
    }

    @Override
    public void union(int p, int q) {
        int rootP = find(p);

        data[rootP] = q;
        count--;
    }

    @Override
    public int find(int p) {
        while (data[p] != p) {
            p = data[p];
        }
        return data[p];
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

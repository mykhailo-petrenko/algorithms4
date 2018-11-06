package uf;

public class BasicUF implements UF {
    private int[] data;
    private int count;
    private int N;

    public BasicUF(int N) {
        this.N = N;
        data = new int[N];
        count = N;

        for (int i = 0; i < N; i++) {
            data[i] = i;
        }
    }


    @Override
    public void union(int p, int q) {
        int groupP = find(p);
        int groupQ = find(q);

        if (groupP == groupQ) {
            return;
        }

        merge(groupP, groupQ);
        count--;
    }

    @Override
    public int find(int p) {
        return data[p];
    }

    @Override
    public boolean connected(int p, int q) {
        int groupP = find(p);
        int groupQ = find(q);

        return  groupP == groupQ;
    }

    @Override
    public int count() {
        return count;
    }

    private void connect(int p, int groupId) {
        data[p] = groupId;
    }

    private void merge(int groupFrom, int groupTo) {

        for (int i = 0; i < N; i++) {
            if (data[i] == groupFrom) {
                connect(i, groupTo);
            }
        }
    }
}

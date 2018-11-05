package uf;

public class BasicUF implements UF {
    private int[] data;
    private int count;
    private int N;

    public BasicUF(int N) {
        this.N = N;
        data = new int[N];
        count = 0;
    }


    @Override
    public void union(int p, int q) {
        int groupP = find(p);
        int groupQ = find(q);

        if (groupP == 0 && groupQ == 0) {
            count++;
            connect(p, count());
            connect(q, count());
        } else if (groupP == 0) {
            connect(p, groupQ);
        } else if (groupQ == 0) {
            connect(q, groupP);
        } else {
            merge(groupP, groupQ);
        }
    }

    @Override
    public int find(int p) {
        return data[p];
    }

    @Override
    public boolean connected(int p, int q) {
        int groupP = find(p);
        int groupQ = find(q);

        return groupP != 0 && groupP == groupQ;
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

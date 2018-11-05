package uf;

public interface UF {
    /**
     * Add connection between P and Q
     * @param p int Node P
     * @param q int Node Q
     */
    void union(int p, int q);

    /**
     * Resolve component ID for P
     * @param p int Node
     * @return int Compomnent ID
     */
    int find(int p);

    /**
     * Is P and Q in the same component
     * @param p int Node P
     * @param q int Node Q
     * @return boolean is connected
     */
    boolean connected(int p, int q);

    /**
     * Number of components
     * @return int
     */
    int count();
}

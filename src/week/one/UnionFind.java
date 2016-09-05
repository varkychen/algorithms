package week.one;

public interface UnionFind {
    int findComponent(int p);

    void union(int p, int q);

    boolean isConnected(int p, int q);

    int countComponents();

    void printData();
}

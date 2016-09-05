package week.one;

public class WeightedQuickUnion extends AbstractUnionFind {

    private int[] sz;

    public WeightedQuickUnion(int n) {
        super(n);
        sz = new int[n];
        for (int i = 0; i < sz.length; i++)
            sz[i] = 1;
    }

    @Override
    public int findComponent(int p) {
        while (p != array[p])
            p = array[p];
        return p;
    }

    @Override
    public void union(int p, int q) {
        int proot = findComponent(p);
        int qroot = findComponent(q);

        if (sz[proot] < sz[qroot]) {
            array[proot] = qroot;
            sz[qroot] += sz[proot];
        } else {
            array[qroot] = proot;
            sz[proot] += sz[qroot];
        }
        count--;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return findComponent(p) == findComponent(q);
    }
}

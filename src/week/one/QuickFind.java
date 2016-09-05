package week.one;

public class QuickFind extends AbstractUnionFind {

    public QuickFind(int n) {
        super(n);
    }

    @Override
    public int findComponent(int p) {
        return array[p];
    }

    @Override
    public void union(int p, int q) {
        int pid = array[p];
        int qid = array[q];
        for (int i = 0; i < array.length; i++) {
            if (array[i] == qid)
                array[i] = pid;
        }
        count--;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return findComponent(p) == findComponent(q);
    }
}

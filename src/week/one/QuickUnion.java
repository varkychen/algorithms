package week.one;

/*
 * In Quick-Union, the data structure used is a tree.
 * The tree is implemented using an array. If the value
 * of an element equals it's index, it is the root of 
 * a tree.
 * 
 */
public class QuickUnion extends AbstractUnionFind {

    public QuickUnion(int n) {
        super(n);
    }

    /*
     * The root of the tree is it's component
     */
    @Override
    public int findComponent(int i) {
        while (i != array[i])
            i = array[i];
        return i;
    }

    /*
     * Connect the root of the cells together
     */
    @Override
    public void union(int p, int q) {
        int pid = findComponent(p);
        int qid = findComponent(q);
        array[qid] = pid;
        count--;
    }

    /*
     * Two cells are connected if they have the same root, i.e, if they belong
     * to the same component.
     */
    @Override
    public boolean isConnected(int p, int q) {
        return findComponent(p) == findComponent(q);
    }
}

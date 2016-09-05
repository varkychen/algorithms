package week.one;

import edu.princeton.cs.algs4.StdOut;

public abstract class AbstractUnionFind implements UnionFind {
    protected int count;
    protected int[] array;

    public AbstractUnionFind(int n) {
        count = n;
        array = new int[n];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
    }

    @Override
    public int countComponents() {
        return count;
    }

    @Override
    public void printData() {
        for (int i : array) {
            StdOut.print(i + " ");
        }
        StdOut.println();
    }
}
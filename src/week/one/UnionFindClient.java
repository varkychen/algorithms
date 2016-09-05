package week.one;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class UnionFindClient {

    public static void main(String[] args) {
        System.setIn(ClassLoader.getSystemResourceAsStream("tinyUF.txt"));
        int n = StdIn.readInt();
        UnionFind uf = new WeightedQuickUnionPathCompression(n);
        StdOut.print("      ");
        uf.printData();
        StdOut.println("----------------------------------");

        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();

            StdOut.print("(" + p + "," + q + ") ");
            if (!uf.isConnected(p, q)) {
                uf.union(p, q);
                uf.printData();
            } else {
                StdOut.println("- Skipped");
            }
        }
        StdOut.println(uf.countComponents() + " components");
    }
}

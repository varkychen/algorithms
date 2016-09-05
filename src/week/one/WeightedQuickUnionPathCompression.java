package week.one;

public class WeightedQuickUnionPathCompression extends WeightedQuickUnion {

    public WeightedQuickUnionPathCompression(int n) {
        super(n);
    }

    @Override
    public int findComponent(int p) {
        while (p != array[p]) {
            array[p] = array[array[p]];
            p = array[p];
        }
        return p;
    }
}

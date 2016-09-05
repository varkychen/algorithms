import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/*
 * @author  Varghese Paul
 * Date :   4-Sep-2016
 * Purpose: Percolation algorithm
 * Usage:   java Percolation 
 *          <grid-size> 
 *          <connection pair 1>
 *          <connection pair 2>
 *          <connection pair 3>
 */
public class Percolation {
    /*
     * isOpen represents if any cell in the grid has been opened. It has (N*N+2)
     * elements for consistency with the percolation grid.
     */
    private boolean[] openGrid;

    /*
     * Represents the bottom virtual connector site
     */
    private int bottomConnector;

    /*
     * Order of the percolation grid. Equal to the row size or column size of
     * the percolation grid.
     */
    private int n;

    /*
     * An N*N percolation grid + 2 virtual connector sites. It will contain
     * (N*N)+2 elements. The grid elements can be references as (1,1) to (N,N).
     * 
     * Element (i,j) refers to ((i-1)*N+(j)) array element
     *
     * If any cell (1,*) is opened, it will get connected to top virtual
     * connector at id[0]. If any cell (N,*) is opened, it will get connected to
     * the bottom virtual connector at id[N*N+1].
     */
    private WeightedQuickUnionUF percolationGrid;

    /*
     * Initialize the percolation array.
     */
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("N cannot be 0 or lesser");

        this.n = n;
        this.bottomConnector = n * n + 1;
        openGrid = new boolean[n * n + 2];
        percolationGrid = new WeightedQuickUnionUF(n * n + 2);
    }

    /*
     * Open cell at coordinate (i,j). Validates that the given coordinates are
     * in the prescribed range. Then, marks the coordinate as open. Then
     * connects with the left, right, upper and lower sites only if they are
     * valid coordinates and only if the site is already open.
     * 
     */
    public void open(int i, int j) {
        int id = locate(i, j);
        openGrid[id] = true;

        if (i == 1)
            percolationGrid.union(0, id);
        if (i == n)
            percolationGrid.union(bottomConnector, id);

        connectToSite(id, i - 1, j); // connect with left neighbour
        connectToSite(id, i + 1, j); // connect with right neighbour
        connectToSite(id, i, j - 1); // connect with above neighbour
        connectToSite(id, i, j + 1); // connect with below neighbour
    }

    /*
     * Check if the given cell is open. Validates that the given coordinates are
     * in the prescribed range.
     */
    public boolean isOpen(int i, int j) {
        int id = locate(i, j);
        return openGrid[id];
    }

    /*
     * Check if the given cell is full, i.e. if it is connected to the top row.
     * Validates that the given coordinates are in the prescribed range.
     */
    public boolean isFull(int i, int j) {
        int id = locate(i, j);
        return percolationGrid.connected(0, id);
    }

    /*
     * Checks if the given system percolates. Only need to see if the two
     * virtual connector sites are connected.
     */
    public boolean percolates() {
        return percolationGrid.connected(0, bottomConnector);
    }

    /*
     * Validate input cell coordinate (p,q) is within range [1,N]
     */
    private void validate(int p, int q) {
        if (p < 1 || p > n)
            throw new IndexOutOfBoundsException("Invalid cell index");
        if (q < 1 || q > n)
            throw new IndexOutOfBoundsException("Invalid cell index");
    }

    /*
     * Identify location of coordinate (p,q) on the percolationGrid. Validates
     * that the given coordinates are in the prescribed range. Calculate the
     * location using the formula (p-1)*N+q.
     */
    private int locate(int p, int q) {
        validate(p, q);
        return (p - 1) * n + q;
    }

    /*
     * Connect to the given site. Ensure given site is valid and open and not
     * already connected. Then, and only then, connect.
     */
    private void connectToSite(int id, int i, int j) {
        try {
            int newId = locate(i, j);
            if (openGrid[newId] && !percolationGrid.connected(id, newId)) {
                percolationGrid.union(id, newId);
            }
        } catch (IndexOutOfBoundsException e) {
//            StdOut.printf("Site (%d,%d) is invalid, ignoring%n", i, j);
        }
    }

    public static void main(String[] args) {
        System.setIn(ClassLoader.getSystemResourceAsStream("input2.txt"));

        int n = StdIn.readInt();
        StdOut.println("N: " + n);

        Percolation perc = new Percolation(n);
        while (!perc.percolates()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            perc.open(p, q);

            StdOut.printf("(%d,%d) : Has system percolated? %s%n", p, q, 
                    (perc.percolates() ? "Yes" : "No"));
        }
    }
}

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/*
 * @author  Varghese Paul
 * Date :   5-Sep-2016
 * Purpose: The PercolationStats class is used to calculate the percolation 
 *          threshold
 * Usage:   java Percolation <grid-size> <number of trials> 
 */
public class PercolationStats {

    /*
     * The order of the Percolation Grid for the experiment.
     */
    private int n;
    
    /*
     * The number of times percolation threshold needs to be calculated for the
     * given size of Percolation Grid.
     */
    private int trials;
    
    /*
     * Observations of the percolation threshold for each trial. The threshold 
     * is the ratio of open sites versus total number of sites.
     */
    private double[] observations;
    
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException(
                    "N or trials is less than or equal to zero");
        
        this.n = n;
        this.trials = trials;
        observations = new double[trials];
        executeTrials();
    }
    
    /*
     * Calculate the mean of the observations
     */
    public double mean() {
        return StdStats.mean(observations);
    }

    /*
     * Calculate the stddev of observations
     */
    public double stddev() {
        return StdStats.stddev(observations);
    }
    
    /*
     * Calculate the lower value of confidence level
     */
    public double confidenceLo() {
        double mean = mean();
        double stddev = stddev();
        double rootT = Math.sqrt(trials);
        return mean - (1.96D * stddev) / rootT;
    }
    
    /*
     * Calculate the upper value of confidence level
     */
    public double confidenceHi() {
        double mean = mean();
        double stddev = stddev();
        double rootT = Math.sqrt(trials);
        return mean + (1.96D * stddev) / rootT;
    }
    
    /*
     * Execute trials and record each percolation threshold. 
     */
    private void executeTrials() {
        for (int i = 0; i < trials; i++) {
            int count = 0;
            Percolation p = new Percolation(n);
            while (!p.percolates()) {
                int x = StdRandom.uniform(1, n+1);
                int y = StdRandom.uniform(1, n+1);
                if (!p.isOpen(x, y)) {
                    p.open(x, y);
                    count++;
                }
            }
            observations[i] = ((double) count) / (n*n);
        }
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int count = Integer.parseInt(args[1]);
        
        PercolationStats stats = new PercolationStats(n, count);
        StdOut.println("mean                   = " + stats.mean());
        StdOut.println("stddev                 = " + stats.stddev());
        StdOut.printf("95%% confidence interval = %f, %f%n", 
                stats.confidenceLo(), stats.confidenceHi());
    }
}

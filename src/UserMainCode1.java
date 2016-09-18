import java.util.Arrays;

// Read only region start
public class UserMainCode1 {

    public String minDiff(int input1, double[] input2, double input3) {
        // Read only region end
        // Write code here...
        double maxRadius = maxRadius(input2);
        double oldDiff = 0d;
        double newDiff = 0.1d;
        double sum = 0d;
        do {
            sum = calculateArea(input2, oldDiff);
            System.out.println(sum);
            if (sum <= input3)
                break;
            
            oldDiff = newDiff * maxRadius;
            newDiff += 0.1d;
        }   while (true);

        System.out.println("before estimator...");
        
        if (oldDiff > 0d) {
            double estimate = 0d;        
            while (oldDiff - estimate >= 1E-6) {
                estimate = (oldDiff - estimate) / 2;
                if (calculateArea(input2, estimate) < input3) {
                    oldDiff = estimate;
                    estimate = 0d;
                } else if (calculateArea(input2, estimate) == input3) {
                    oldDiff = estimate;
                    break;
                }
            }
        }
        return String.format("%.6f", oldDiff);
        
    }
    private double maxRadius(double[] input2) {
        double max = 0d;
        for (int i = 0; i < input2.length; i++)
            if (max < input2[i])
                max = input2[i];
        return max;
    }
    
    private double calculateArea(double[] input2, double oldDiff) {
        double pi = 3.14d;
        double sum = 0d;
        for (int i = 0; i < input2.length; i++) {
            double r = input2[i] - oldDiff;
            sum += pi * r * r;
        }
        return sum;
    }
    public static void main(String[] args) {
        UserMainCode1 code = new UserMainCode1();
        System.out.println(code.minDiff(2, new double[] {1.0d, 2.0d, 14.0}, 16.0d));
    }
}
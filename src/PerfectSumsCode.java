import java.util.Stack;

// Read only region start
public class PerfectSumsCode
{

    public int perfectSums(int input1, int[] input2, int input3) {
        // Read only region end
        // Write code here...
        int count = 0;
        for (int i = 0; i < input2.length - 1; i++) {
            int j = i;
            int sum = 0;
            Stack<Integer> stack = new Stack<Integer>();
            do {
                if (j == input2.length) {
                    j = stack.pop();
                    sum -= input2[j++];
                    continue;
                }
                int tempSum = sum + input2[j];
                if (tempSum == input3 && stack.empty()) break;
                
                if (tempSum < input3) {
                    sum = tempSum;
                    stack.push(j++);
                } else if (tempSum == input3) {
                    stack.forEach(c -> System.out.print(input2[c] + " + "));
                    System.out.println(input2[j]);
                    count++;
                    j++;
                } else {
                    j++;
                }
            } while(!stack.empty());
        }
        return count;
    }
    
    public static void main(String[] args) {
        PerfectSumsCode code = new PerfectSumsCode();
        System.out.println(code.perfectSums(6, new int[] {4, 10, 3, 2, 5, 8, 6, 10}, 10));
    }
}
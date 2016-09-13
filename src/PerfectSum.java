import java.util.Stack;

// Read only region start
class UserMainCode {

    public int PerfectSums(int input1,int[] input2,int input3) {
        if (input1 == 0) return 0;
        else if (input3 < 0) return 0;
        else if (input2[input1-1] == input3)
            return 1;
        else return PerfectSums(input1-1, input2, input3 - input2[input1 - 1]);
    }
    
    private int count;
    private int[] stack;
    private int last;
    
    private void push(int i) {
        stack[last++] = i;
    }
    
    private void pop() {
        stack[--last] = 0;
    }
    
    private int sum() {
        int sum = 0;
        for (int i=0; i<last; i++)
            sum += stack[i];
        return sum;
    }
}
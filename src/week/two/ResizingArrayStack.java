package week.two;

import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class ResizingArrayStack<Item> implements Iterable<Item> {

    private int n;
    @SuppressWarnings("unchecked")
    private Item[] stack = (Item[]) new Object[1];
    
    public void push(Item item) {
        if (n == stack.length)
            resize(2 * stack.length);
        stack[n++] = item;
    }
    
    public Item pop() {
        Item result = stack[--n];
        stack[n] = null;
        
        if (n > 0 && n == stack.length/4)
            resize(stack.length/2);
        return result;
    }

    public boolean isEmpty() {
        return n == 0;
    }
    
    public int size() {
        return n;
    }
    
    private void resize(int capacity) {
        @SuppressWarnings("unchecked")
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = stack[i];
        }
        stack = temp;
    }
    
    public static void main(String[] args) {
        ResizingArrayStack<String> stack = new ResizingArrayStack<String>();
        while (!StdIn.isEmpty()) {
            String token = StdIn.readString();
            if (token.equals("-"))
                StdOut.print(stack.pop() + " - ");
            else
                stack.push(token);
            
            StdOut.print("[");
            stack.forEach(c -> StdOut.print(c + " -> "));
            StdOut.println("null]");
        }
    }

    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }
    
    private class ReverseArrayIterator implements Iterator<Item> {
        private int i = n;
        @Override public boolean hasNext() { return i > 0; }
        @Override public Item next() { return stack[--i]; }
    }
}

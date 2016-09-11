package week.two;

import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class LinkedStack<T> implements Iterable<T> {
    private class Node {
        private T item;
        private Node next;
    }
    
    private Node first;
    
    public boolean isEmpty() {
        return first == null;
    }
    
    public void push(T item) {
        Node old = first;
        first = new Node();
        first.item = item;
        first.next = old;
    }
    
    public T pop() {
        T item = first.item;
        first = first.next;
        return item;
    }

    public static void main(String[] args) {
        LinkedStack<String> stack = new LinkedStack<String>();
        while (!StdIn.isEmpty()) {
            String token = StdIn.readString();
            if (token.equals("-"))  StdOut.println(stack.pop());
            else                    stack.push(token);
            
            StdOut.print("[");
            stack.forEach(c -> StdOut.print(c + " -> "));
            StdOut.println("null]");
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ListStackIterator();
    }
    
    private class ListStackIterator implements Iterator<T> {
        private Node current = LinkedStack.this.first;
        
        @Override public T next() {
            T t = current.item;
            current = current.next;
            return t;
        }
        
        @Override public boolean hasNext() { 
            return current != null; }
    }
}

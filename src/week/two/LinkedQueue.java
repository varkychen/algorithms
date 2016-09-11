package week.two;

import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class LinkedQueue<Item> implements Iterable<Item> {
    
    private class Node {
        private Item item;
        private Node next;
    }
    
    private Node first;
    private Node last;
    
    public void enqueue(Item item) {
        Node temp = last;
        last = new Node();
        last.item = item;
        if (isEmpty()) first = last;
        else temp.next = last;
    }
    
    private Item dequeue() {
        Item item = first.item;
        first = first.next;
        if (isEmpty()) last = null;
        return item;
    }
    
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListQueueIterator();
    }
    
    private class ListQueueIterator implements Iterator<Item> {
        
        private Node first = LinkedQueue.this.first;

        @Override public boolean hasNext() { return first != null; }

        @Override
        public Item next() {
            Item item = first.item;
            first = first.next;
            return item;
        }
    }
    
    public static void main(String[] args) {
        LinkedQueue<String> queue = new LinkedQueue<String>();
        while (!StdIn.isEmpty()) {
            String token = StdIn.readString();
            if (token.equals("-")) StdOut.print(queue.dequeue() + " - ");
            else queue.enqueue(token);
            
            StdOut.print("[");
            queue.forEach(s -> StdOut.print(s + " -> "));
            StdOut.println("null]");
        }
    }
}
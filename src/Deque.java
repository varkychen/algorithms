import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {
    
    private class Node {
        private Item item;
        private Node next;
        private Node previous;
    }

    private int size;
    private Node first;
    private Node last;
    
    public boolean isEmpty() { return size == 0; }
    public int size() { return size; }
    
    public void addFirst(Item item) {
        doNullCheck(item);
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        if (last == null) {
            last = first;
        } else {
            first.next = oldFirst;
            oldFirst.previous = first;
        }
        size++;
    }
    
    public void addLast(Item item) {
        doNullCheck(item);
        Node oldLast = last;
        last = new Node();
        last.item = item;
        if (first == null) {
            first = last;
        } else {
            oldLast.next = last;
            last.previous = oldLast;
        }
        size++;
    }
    
    public Item removeFirst() {
        doEmptyCheck();
        Item item = first.item;
        first.item = null;
        
        if (first == last) {
            first = null;
            last = null;
        } else {
            first = first.next;
            first.previous = null;
        }
        size--;
        return item;
    }
    
    public Item removeLast() {
        doEmptyCheck();
        Item item = last.item;
        last.item = null;
        
        if (first == last) {
            first = null;
            last = null;
        }
        else {
            last = last.previous;
            last.next = null;
        }
        size--;
        return item;
    }
    
    private void doEmptyCheck() {
        if (size == 0) throw new NoSuchElementException("Deque is empty !!!");
    }
    
    private void doNullCheck(Item item) {
        if (item == null)
            throw new NullPointerException("Can't add null objects !!!");
    }
    
    @Override
    public Iterator<Item> iterator() {
        return new ForwardIterator();
    }

    private class ForwardIterator implements Iterator<Item> {
        
        private Node first = Deque.this.first;

        @Override public boolean hasNext() { return first != null; }
        
        @Override 
        public Item next() {
            if (first == null) throw new NoSuchElementException("Empty Dequeue !!!");
            Item item = first.item;
            first = first.next;
            return item;
        }
    }
    
    public static void main(String[] args) {
        Deque<String> deque = new Deque<String>();
        while (!StdIn.isEmpty()) {
            String token = StdIn.readString();
            if (token.equals("-")) StdOut.print(deque.removeFirst() + " - ");
            else deque.addFirst(token);
            
            StdOut.print("[ ");
            deque.forEach(c -> StdOut.print(c + " "));
            StdOut.println("]");
        }
    }
}

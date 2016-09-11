package week.two;

import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class ResizingArrayQueue<T> implements Iterable<T> {

    private int first, last, size;
    private T[] queue;
    
    public ResizingArrayQueue() { 
        queue = (T[]) new Object[1]; 
    }

    @Override 
    public Iterator<T> iterator() { 
        return new ForwardIterator(); 
    }
    
    public void enqueue(T t) {
        if (last == queue.length) resizeOrReset();
        queue[last++] = t;
        size++;
    }
    
    private void resizeOrReset() {
        if (size > queue.length/2) resize(2*queue.length);
        else reset();
    }
    
    private void resize(int capacity) {
        T[] temp = queue;
        queue = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++)
            queue[i] = temp[first + i];
        first = 0;
        last = size;
    }
    
    private void reset() {
        for (int i = 0; i < size; i++)
            queue[i] = queue[first + i];
        first = 0;
        last = size;
    }
    
    public T dequeue() {
        T item = queue[first];
        queue[first++] = null;
        size--;
        if (size < queue.length/4) resize(queue.length/2);
        return item;
    }
    
    public int size() {
        return size;
    }
    
    private class ForwardIterator implements Iterator<T> {
        
        private int current;
        private int size = ResizingArrayQueue.this.queue.length;

        @Override 
        public boolean hasNext() { 
            return current < size;
        }
        
        @Override 
        public T next() {
            return queue[current++];
        }
    }

    public static void main(String[] args) {
        ResizingArrayQueue<String> queue = new ResizingArrayQueue<String>();
        while (!StdIn.isEmpty()) {
            String token = StdIn.readString();
            if (token.equals("-")) StdOut.print(queue.dequeue() + " - ");
            else queue.enqueue(token);
            
            StdOut.print("[ ");
            queue.forEach(s -> StdOut.print(s + " "));
            StdOut.println("]");
        }
    }
}

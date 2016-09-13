import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    
    int size;
    Item[] randomQueue;
    
    public RandomizedQueue() {
        randomQueue = (Item[]) new Object[1];
    }
    
    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }

    public void enqueue(Item item) {
        if (item == null) 
            throw new NullPointerException("Won't entertain null items !!!");
        if (size == randomQueue.length) resize(2*randomQueue.length);
        randomQueue[size++] = item;
    }

    private void resize(int capacity) {
        Item[] temp = randomQueue;
        randomQueue = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++)
            randomQueue[i] = temp[i];
    }

    public Item dequeue() {
        doEmptyCheck();
        if (size > 1) {
            int i = StdRandom.uniform(size);
            Item temp = randomQueue[size-1];
            randomQueue[size-1] = randomQueue[i];
            randomQueue[i] = temp;
        }
        
        Item item = randomQueue[size-1];
        randomQueue[--size] = null;
        if (size < randomQueue.length/4) resize(randomQueue.length/2);
        return item;
    }
    
    public Item sample() {
        doEmptyCheck();
        int i = StdRandom.uniform(size);
        return randomQueue[i];
    }

    private void doEmptyCheck() {
        if (size == 0) 
            throw new NoSuchElementException("Queue is empty!!!");
    }
    
    @Override
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }
    
    private class RandomIterator implements Iterator<Item> {
        
        private Item[] data;
        private int current;
        
        RandomIterator() {
            data = (Item[]) new Object[size];
            for (int i = 0; i < size; i++) {
                data[i] = RandomizedQueue.this.randomQueue[i];
            }
            StdRandom.shuffle(data);
        }

        @Override public boolean hasNext() { return current < data.length; }
        
        @Override public Item next() {
            if (current == data.length) 
                throw new NoSuchElementException("No more elements !!!");
            return data[current++]; 
        }
    }
}

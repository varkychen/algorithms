import static org.hamcrest.core.Is.is;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.princeton.cs.algs4.StdOut;

public class RandomizedQueueTest {

    private RandomizedQueue<String> queue;
    private String data = "Tiger1! Tiger2! burning bright In the1 forest of the2 "
          + "night What immortal hand or eye Could frame thy fearful symmetry!";

    @Before
    public void setUp() throws Exception {
        queue = new RandomizedQueue<>();
    }

    @Test
    public void testEnqueueOne() {
        queue.enqueue("Varghese");
        Assert.assertThat(queue.size, is(1));
    }

    @Test
    public void testArrayUpsizeOnEnqueue() {
        queue.enqueue("Varghese");
        queue.enqueue("Paul");
        
        Assert.assertThat(queue.size, is(2));
        Assert.assertThat(((Object[]) queue.randomQueue).length, is(2));
    }

    @Test
    public void testDequeue() {
        queue.enqueue("Varghese");
        String output = queue.dequeue();
        
        Assert.assertThat(output, is("Varghese"));
        Assert.assertThat(queue.size, is(0));
        Assert.assertThat(((Object[]) queue.randomQueue).length, is(1));
    }
    
    @Test
    public void testArrayDownsizeOnDequeue() {
        initializeData();
        
        Assert.assertThat(queue.size, is(20));
        Assert.assertThat(((Object[]) queue.randomQueue).length, is(32));
        
        IntStream.range(0, 13).forEach(i -> queue.dequeue());
        
        Assert.assertThat(queue.size, is(7));
        Assert.assertThat(((Object[]) queue.randomQueue).length, is(16));
    }
    
    @Test
    public void testFillEmptyAgainFill() {
        initializeData();
        queue.forEach(i -> queue.dequeue());
        initializeData();
        Assert.assertThat(queue.size, is(20));
        Assert.assertThat(((Object[]) queue.randomQueue).length, is(32));
    }

    private void initializeData() {
        Arrays.stream(data.split(" ")).forEach(queue::enqueue);
    }
    
    @Test
    public void testRandomness() {
        int noOfTests = 1000000;
        String[] split = data.split(" ");
        int average = noOfTests/split.length;
        double delta = average * 0.015;
        
        Map<String, Integer> histogram = new HashMap<>();
        
        IntStream.range(0, noOfTests).forEach(i -> {
            RandomizedQueue<String> testQueue = new RandomizedQueue<>();
            Arrays.stream(split).forEach(testQueue::enqueue);
            String sample = testQueue.dequeue();
            histogram.put(sample, histogram.getOrDefault(sample, 0) + 1);
        });
        
        histogram.forEach((k, v) -> {
            StdOut.println(k + " : " + v);
            Assert.assertEquals(average, v, delta);
        });
    }
    
    @Test
    public void testIterator() {
        initializeData();
        IntStream.range(0, 10).forEach(i -> {
            StdOut.print(i + " -> [ ");
            queue.forEach(s -> StdOut.print(s + " "));
            StdOut.println("]");
        });
    }
    
    @Test
    public void testMultipleSimultaneousIterators() {
        initializeData();
        int limit = 10;
        List<Iterator<String>> iteratorList = new ArrayList<>();
        List<StringBuilder> builderList = new ArrayList<>();
        IntStream.range(0, 10).forEach(i -> {
            iteratorList.add(queue.iterator());
            builderList.add(new StringBuilder());
        });
        
        while (iteratorList.get(0).hasNext()) {
            IntStream.range(0, 10).forEach(i -> {
                builderList.get(i).append(iteratorList.get(i).next() + " ");
            });
        }
        
        builderList.forEach(StdOut::println);
    }
}

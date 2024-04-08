package runner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class ExecutorExample {
    public static void main(String[] args) {
        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10); // Shared queue between producer and consumer
        ExecutorService executor = Executors.newFixedThreadPool(2); // Thread pool with 2 threads

        // Producer task
        Runnable producerTask = () -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    System.out.println("Producing: " + i);
                    queue.put(i); // Put item into the queue
                    Thread.sleep(1000); // Simulate some processing time
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        // Consumer task
        Runnable consumerTask = () -> {
            try {
                for (int i = 0; i < 10; i++) {
                    Integer value = queue.take(); // Take item from the queue
                    System.out.println("Consuming: " + value);
                    Thread.sleep(2000); // Simulate some processing time
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        // Submit tasks to the executor
        executor.submit(producerTask);
        executor.submit(consumerTask);

        // Shutdown the executor
        executor.shutdown();
    }
}

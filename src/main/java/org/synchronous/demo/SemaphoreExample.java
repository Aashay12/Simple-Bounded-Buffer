package org.synchronous.demo;

import java.io.PrintStream;
import java.util.concurrent.Semaphore;

public class SemaphoreExample {
    private static final Semaphore semaphore = new Semaphore(2);

    public static void main(String[] args) {
        System.out.println("\nOnly two threads will be running at a time. Added a delay of 5 secs to simulate a critical task\n");
        int numberOfThreads = 5;

        for(int i = 0; i < numberOfThreads; ++i) {
            long startTime = System.currentTimeMillis();
            Thread thread = new Thread(() -> {
                try {
                    semaphore.acquire();
                    long waitTime = System.currentTimeMillis() - startTime;
                    long var10001 = Thread.currentThread().threadId();
                    System.out.println("Thread" + var10001 + " waited for " + (double)waitTime / 1000.0 + " is accessing the shared resource");
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            });
            thread.start();
        }
    }
}

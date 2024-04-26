package org.synchronous.demo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MutexExample {
    private static final Lock mutex = new ReentrantLock();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            mutex.lock(); // Acquiring the mutex
            try {
                // Critical section - Accessing shared resource
                System.out.println("Thread 1 is accessing the shared resource.");
                Thread.sleep(2000); // Simulating some task
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                mutex.unlock(); // Releasing the mutex
            }
        });

        Thread t2 = new Thread(() -> {
            mutex.lock(); // Acquiring the mutex
            try {
                // Critical section - Accessing shared resource
                System.out.println("Thread 2 is accessing the shared resource.");
                Thread.sleep(2000); // Simulating some task
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                mutex.unlock(); // Releasing the mutex
            }
        });

        t1.start();
        t2.start();
    }
}
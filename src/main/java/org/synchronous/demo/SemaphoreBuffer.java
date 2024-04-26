package org.synchronous.demo;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

class SemaphoreBuffer {
    private Queue<Integer> buffer = new LinkedList();
    private Semaphore count = new Semaphore(0);
    private Semaphore mutex = new Semaphore(1);
    private Semaphore limit;
    public SemaphoreBuffer(int limit) {
        this.limit = new Semaphore(limit);
    }

    public void add(Integer item) throws InterruptedException {
        this.limit.acquire();
        this.mutex.acquire();
        this.buffer.add(item);
        this.mutex.release();
        this.count.release();
    }

    public Integer remove() throws InterruptedException {
        Integer item = null;
        this.count.acquire();
        this.mutex.acquire();
        item = (Integer)this.buffer.remove();
        this.mutex.release();
        this.limit.release();
        return item;
    }

    public int getCount() {
        return this.count.availablePermits();
    }

    public int getLimit() {
        return this.limit.availablePermits();
    }

    public int getMutex() {
        return this.mutex.availablePermits();
    }
}
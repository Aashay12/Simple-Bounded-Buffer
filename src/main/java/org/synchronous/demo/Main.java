package org.synchronous.demo;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        SemaphoreBuffer buffer = new SemaphoreBuffer(15);
        Thread producer_1 = new Thread(new SemaphoreProducer(buffer, "PROD_1", 5));
        Thread producer_2 = new Thread(new SemaphoreProducer(buffer, "PROD_2", 5));
        Thread consumer_A = new Thread(new SemaphoreConsumer(buffer, "A", 5));
        Thread consumer_B = new Thread(new SemaphoreConsumer(buffer, "B", 5));
        Thread consumer_C = new Thread(new SemaphoreConsumer(buffer, "C", 5));

        producer_1.start();
        producer_2.start();
        consumer_A.start();
        consumer_B.start();
        consumer_C.start();

        producer_1.join();
        producer_2.join();
        consumer_A.join();
        consumer_B.join();
        consumer_C.join();
    }
}
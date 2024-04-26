package org.synchronous.demo;

import java.security.SecureRandom;
import java.util.Random;
public class SemaphoreProducer implements Runnable{

    private SemaphoreBuffer buffer;
    private String name;
    private Integer productionRate; // Rate at which producer publishes the message

    public SemaphoreProducer(SemaphoreBuffer buffer, String name, Integer productionRate) {
        super();
        this.buffer = buffer;
        this.name = name;
        this.productionRate = productionRate;
    }

    @Override
    public void run() {
        Random random = new SecureRandom();
        while(true){
            try{
                int item = random.nextInt(100);
                buffer.add(item);
                System.out.println(Thread.currentThread().getName() + " Producer: " + name + " added " + item + " to buffer.");
                Thread.sleep(productionRate * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
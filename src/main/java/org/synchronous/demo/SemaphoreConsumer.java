package org.synchronous.demo;

public class SemaphoreConsumer implements Runnable {
    private SemaphoreBuffer buffer;
    private String name;
    private Integer consumptionRate = 0;

    public SemaphoreConsumer(SemaphoreBuffer buffer, String name, Integer consumptionRate) {
        this.buffer = buffer;
        this.name = name;
        this.consumptionRate = consumptionRate;
    }

    public void run() {
        while(true) {
            try {
                int item = this.buffer.remove();
                System.out.println(Thread.currentThread().getName() + "  Consumer: " + this.name + " removed " + item);
                Thread.sleep(this.consumptionRate * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
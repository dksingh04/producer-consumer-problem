package com.dk.pc.processes;

import com.dk.pc.PCMessagingQueue;

public class ProducerThread<T> implements Runnable{
    private PCMessagingQueue<T> messageQueue;
    private T message;

    public ProducerThread(PCMessagingQueue<T> messageQueue, T message){
        this.message = message;
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        try {
            System.out.println("Producing : "+ message);
            this.messageQueue.produce(message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

package com.dk.pc.processes;

import com.dk.pc.PCMessagingQueue;

public class ConsumerThread<T> implements Runnable{

    private PCMessagingQueue<T> messageQueue;

    public ConsumerThread(PCMessagingQueue<T> messageQueue){
        this.messageQueue = messageQueue;
    }
    @Override
    public void run() {

        try {
            T message = messageQueue.consume();
            System.out.println("Consumed : "+ message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

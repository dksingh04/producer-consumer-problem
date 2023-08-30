package com.dk.pc.blockingq.impl;

import com.dk.pc.PCMessagingQueue;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class BlockingQueuePCMessaging<T> implements PCMessagingQueue<T> {
    private int DEFAULT_SIZE = 10;
    private Queue<T> queue;
    private int size;


    public BlockingQueuePCMessaging(){
        this.size = DEFAULT_SIZE;
        this.queue = new ArrayBlockingQueue<>(DEFAULT_SIZE);
    }

    public BlockingQueuePCMessaging(int size){
        this.size = size;
        this.queue = new ArrayBlockingQueue<>(size);
    }

    @Override
    public void produce(T message) throws InterruptedException {
        if(queue.size() == this.size){
            System.out.println("Blocking Message Queue is Full waiting for Consumer to consume some meesages!!");
            wait();
        }
        queue.add(message);
        Thread.sleep(1000);
    }

    @Override
    public T consume() throws InterruptedException {

        if(queue.size() == 0){
            System.out.println("Message Queue is Empty waiting for producer to produce some meesages!!");
            wait();
        }
        T message = queue.poll();
        Thread.sleep(1000);
        return null;
    }
}

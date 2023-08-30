package com.dk.pc.classic.impl;

import com.dk.pc.PCMessagingQueue;

import java.util.ArrayList;
import java.util.List;

public class ClassicPCMessagingQueue<T> implements PCMessagingQueue<T> {

    private int DEFAULT_SIZE = 10;
    private List<T> queue;
    private int size;


    public ClassicPCMessagingQueue(){
        this.size = DEFAULT_SIZE;
        queue = new ArrayList<>(DEFAULT_SIZE);
    }

    public ClassicPCMessagingQueue(int size){
        this.size = size;
        queue = new ArrayList<>(this.size);
    }

    public void produce(T message) throws InterruptedException {
        synchronized(this){
            if(queue.size() == this.size){
                System.out.println("Message Queue is Full waiting for Consumer to consume some meesages!!");
                wait();
            }
            queue.add(message);
            Thread.sleep(1000);
            notify();
        }
    }

    public T consume() throws InterruptedException {

        synchronized (this) {
            if(queue.size() == 0){
                System.out.println("Message Queue is Empty waiting for producer to produce some meesages!!");
                wait();
            }
            T message = queue.remove(0);
            Thread.sleep(1000);
            notify();
            return message;
        }

    }
}

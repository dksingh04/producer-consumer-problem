package com.dk.pc.semaphore.impl;

import com.dk.pc.PCMessagingQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class SemaPhorePCMessagingQueue<T> implements PCMessagingQueue<T> {

    private int DEFAULT_SIZE = 1;
    private List<T> queue;
    private int size;

    private Semaphore producerSemaphore = new Semaphore(1);
    private Semaphore consumerSemaphore = new Semaphore(0);

    public SemaPhorePCMessagingQueue(){
        this.size = DEFAULT_SIZE;
        queue = new ArrayList<>(1);
    }
    public SemaPhorePCMessagingQueue(int size){
        this.size = DEFAULT_SIZE;
        queue = new ArrayList<>(this.size);
    }
    @Override
    public void produce(T message) throws InterruptedException {
        System.out.println("Number of Permits to Producer: "+producerSemaphore.availablePermits());
        System.out.println("Producer Thread is waiting to Acquire the lock to produce message!");
        producerSemaphore.acquire();
        System.out.println("Producer Thread Acquired the lock!");
        queue.add(message);
        Thread.sleep(1000);
        System.out.println("Releasing the lock of Consumer!");
        consumerSemaphore.release();
    }

    @Override
    public T consume() throws InterruptedException {
        System.out.println("Number of Permits to Consumer: "+consumerSemaphore.availablePermits());
        System.out.println("Consumer Thread is waiting to Acquire the lock to consume message!");
        consumerSemaphore.acquire();

        System.out.println("Consumer Thread Acquired the lock!");
        T message = queue.remove(0);
        Thread.sleep(1000);
        System.out.println("Releasing the lock of Producer!");
        producerSemaphore.release();
        return message;
    }
}

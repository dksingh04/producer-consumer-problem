package com.dk.pc;

public interface PCMessagingQueue<T> {

    void produce(T message) throws InterruptedException;
    T consume() throws InterruptedException;

}

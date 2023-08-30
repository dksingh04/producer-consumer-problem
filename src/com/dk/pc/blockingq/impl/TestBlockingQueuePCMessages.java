package com.dk.pc.blockingq.impl;

import com.dk.pc.PCMessagingQueue;
import com.dk.pc.processes.ConsumerThread;
import com.dk.pc.processes.ProducerThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestBlockingQueuePCMessages {

    public static void main(String ...args){
        ExecutorService producerService = Executors.newFixedThreadPool(5);

        ExecutorService consumerService = Executors.newFixedThreadPool(5);
        PCMessagingQueue<Integer> messageQueuenew = new BlockingQueuePCMessaging<>();

        for(int i = 1; i <= 20; i++){
            ProducerThread<Integer> producerThread = new ProducerThread<>(messageQueuenew, i);
            producerService.submit(producerThread);
        }

        for(int i = 1; i <= 8; i++){
            ConsumerThread<Integer> consumerThread = new ConsumerThread<>(messageQueuenew);
            consumerService.submit(consumerThread);
        }


    }
}

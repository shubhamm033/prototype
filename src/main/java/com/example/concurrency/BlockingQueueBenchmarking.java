package com.example.concurrency;

import java.sql.Connection;


public class BlockingQueueBenchmarking {


    public static void main(String[] args) throws InterruptedException {


        BlockingQueueNew blockingQueue = new BlockingQueueNew(10);


        Thread producerThread = new Thread(() -> {
            for (int i = 1; i <= 100000; i++) {
                Connection connection = null;
                try {
                    connection = blockingQueue.dequeue();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                assert connection != null;
                GetData.getData(connection);
                try {
                    blockingQueue.enqueue(connection);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

//                    Thread.sleep(100); // Simulate some work being done
            }
        });

//
        producerThread.start();
    }

}



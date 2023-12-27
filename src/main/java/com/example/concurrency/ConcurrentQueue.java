package com.example.concurrency;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentQueue {

    ArrayList<Integer> arr;
    private static final Lock mutex = new ReentrantLock();

    public ConcurrentQueue(){
        this.arr = new ArrayList<>();

    }

//    public void enque(int number) {
//        mutex.lock();
//        arr.add(number);
//        mutex.unlock();
//    }

    public synchronized void enque(int number) {

        arr.add(number);

    }

    public Integer deque() throws Exception {
        if (this.arr.isEmpty()) {
            throw new Exception("Queue is empty");
        }

        return this.arr.remove(0);


    }


    public Integer getSize() {
        return this.arr.size();
    }

    public static void main(String[] args) throws Exception {

        int numberOfThreads = 2000;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);


        ConcurrentQueue queue = new ConcurrentQueue();

//        queue.enque(1);
//        queue.enque(2);
//        queue.enque(3);
//
//        int x = queue.deque();
//        System.out.println(x);
//        int y = queue.deque();
//        System.out.println(y);


        for(int i=0; i < 1000000000; i++) {

            int finalI = i;

            executorService.submit(() -> queue.enque(finalI));

        }

        System.out.println(queue.getSize());
    }

}


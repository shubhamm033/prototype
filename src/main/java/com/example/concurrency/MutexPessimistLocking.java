package com.example.concurrency;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MutexPessimistLocking {

    Integer number;

    private static final Lock mutex = new ReentrantLock();

    public MutexPessimistLocking() {
        this.number = 0;
    }

    public Integer getNumber() {

        return this.number;
    }


    public void increment(int i) {
        mutex.lock();
        this.number = this.number + i;
        mutex.unlock();
    }




    public static void main(String[] args) throws InterruptedException {


        int numberOfThreads = 200;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);


        MutexPessimistLocking mutexPessimistLocking = new MutexPessimistLocking();

        LocalDateTime start = LocalDateTime.now();
        int count = 1;
        for (int i = 0; i < 1000000; i++) {

            executorService.submit(()-> mutexPessimistLocking.increment(count));
        }
//        Thread.sleep(100000);
        executorService.shutdown();

//        Executor service shutdown is very important here


        System.out.println(mutexPessimistLocking.getNumber());
        String l = String.valueOf(Duration.between(start, LocalDateTime.now()).toMillisPart());
        System.out.printf("Time taken : %s :%n",l);

    }





}

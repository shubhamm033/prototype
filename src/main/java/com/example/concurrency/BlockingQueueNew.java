package com.example.concurrency;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class BlockingQueueNew {

    List<Connection> connectionList = new ArrayList<>();
    Integer limit;

    public BlockingQueueNew(Integer limit) {
        this.limit = limit;
        for (int i = 0; i < this.limit ; i++) {
            Connection connection = DatabaseConnection.getConnection();
            connectionList.add(connection);
        }

    }

    public Boolean isFull() {
        return this.connectionList.size() == this.limit;
    }


    public synchronized void enqueue(Connection connection) throws InterruptedException {

        if (isFull()) {
            wait();
        }

        this.connectionList.add(connection);
        notify();

    }


    public synchronized Connection dequeue() throws InterruptedException {

        if (this.connectionList.isEmpty()) {
            wait();
        }

        Connection connection = this.connectionList.remove(0);
        notify();
        return connection;

    }



}






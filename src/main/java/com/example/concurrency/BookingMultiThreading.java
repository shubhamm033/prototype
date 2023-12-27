package com.example.concurrency;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class BookingMultiThreading extends Thread {


    static Connection conn = DatabaseConnection.getConnection();



    public static void main(String[] args) throws InterruptedException {


        int numberOfThreads = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        // Submit tasks to the thread pool
        for (int i = 1; i <= 100; i++) {

            int finalI = i;
//            Thread.sleep(1000);
            executorService.submit(() -> update(finalI));
//            Thread.sleep(60);
        }

        // Shutdown the thread pool when done
        executorService.shutdown();

    }


    public static void update(int person) {
        Statement statement = null;
        ResultSet resultSet = null;

        try{

            statement = conn.createStatement();


            // Step 4: Execute a query
            String query = "SELECT * FROM booking where person is null order by id limit 1 FOR UPDATE SKIP LOCKED";

            resultSet = statement.executeQuery(query);
//            System.out.println(resultSet);

            // Step 5: Process the result set
            if (resultSet.next()) {
                // Access data from the result set
                String seat = resultSet.getString("seat");
//                System.out.println(resultSet.getString("seat"));
//                String aircraft = resultSet.getString("aircraft");
//                System.out.println(resultSet.getString("aircraft"));
                Integer index = Integer.parseInt(resultSet.getString("id"));
//                System.out.println(resultSet.getString("id"));

                String updateQuery = String.format("Update booking set person = %s where ID = %s", person, index);
                int rowsAffected = statement.executeUpdate(updateQuery);
                // Process the result, if needed
//                System.out.println("Rows affected: " + rowsAffected);
                System.out.printf("Seat %s is booked by user %s%n", seat, person);



            }








        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately in a real application
        } finally {
            // Step 6: Close the resources in a finally block to ensure they are always closed
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

        }







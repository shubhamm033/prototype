package com.example.concurrency;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class GetData {


    public static void getData(Connection connection) {
        Statement statement = null;
        ResultSet resultSet = null;


        try{
            // Step 3: Create a statement
            // Step 3: Create a statement
            assert connection != null;
            statement = connection.createStatement();

            // Step 4: Execute a query
            String query = "SELECT * FROM person limit 1";
            resultSet = statement.executeQuery(query);
//            System.out.println(resultSet);

            // Step 5: Process the result set
            while (resultSet.next()) {
                // Access data from the result set
                System.out.println(resultSet.getString("name"));
//                System.out.println(resultSet.getString("id"));
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




    public static void main(String[] args) {
        Statement statement = null;
        ResultSet resultSet = null;

        try{
            Connection connection = DatabaseConnection.getConnection();
            // Step 3: Create a statement
            // Step 3: Create a statement
            assert connection != null;
            statement = connection.createStatement();

            // Step 4: Execute a query
            String query = "SELECT * FROM person";
            resultSet = statement.executeQuery(query);
            System.out.println(resultSet);

            // Step 5: Process the result set
            while (resultSet.next()) {
                // Access data from the result set
                System.out.println(resultSet.getString("name"));
                System.out.println(resultSet.getString("id"));
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
















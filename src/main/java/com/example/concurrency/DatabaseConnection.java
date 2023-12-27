package com.example.concurrency;

import java.sql.*;

public class DatabaseConnection {

    public static Connection getConnection() {
        Connection connection = null;


        try {
            // Step 1: Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Establish the connection
            String url = "";
            String user = "username";
            String password = "password";
            connection = DriverManager.getConnection(url, user, password);

            return connection;


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately in a real application
        }

        return null;
    }

}
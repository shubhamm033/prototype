package com.example.concurrency;
import com.github.javafaker.Faker;

import java.sql.*;

public class FakerEntryDb {



    public static void main(String[] args) throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;

    try{
            Connection connection = DatabaseConnection.getConnection();
            // Step 3: Create a statement
            statement = connection.createStatement();
            // Create a Faker instance
            Faker faker = new Faker();
            int index = 1;
            for (int i = 1; i <= 4; i++) {

                String alpha = String.valueOf(faker.name().fullName().charAt(i)).toUpperCase();

                for (int j = 1; j <= 25; j++) {

                    // Generate fake data
                    String name = faker.name().fullName();

                    // Print the generated data
                    System.out.println("Name: " + name);

                    String query = String.format("Insert into booking " +
                                    "(id, seat, aircraft) values (%s, '%s', 1)", index,
                             alpha+j);
                    System.out.println(query);
                    int rowsAffected = statement.executeUpdate(query);
                    index += 1;

                    // Process the result, if needed
                    System.out.println("Rows affected: " + rowsAffected);

                }

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

//        System.out.println("Email: " + email);
    }
}


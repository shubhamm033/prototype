package com.example.concurrency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;


@SpringBootApplication
public class ConcurrencyApplication {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(ConcurrencyApplication.class, args);
		System.out.println("hello");


//		PersonRepository personRepository = new PersonRepository();




	}

}

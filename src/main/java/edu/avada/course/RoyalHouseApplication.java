package edu.avada.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RoyalHouseApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(RoyalHouseApplication.class, args);
		for (int i = 0; i < 3; ++i) {
			System.out.print("Hello ");
		}
		System.out.println("Welcome to Royal House!");
	}
}

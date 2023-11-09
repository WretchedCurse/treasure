package com.exercice.treasure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TreasureApplication {

	public static void main(String[] args) {
		System.exit(SpringApplication.exit(SpringApplication.run(TreasureApplication.class, args)));
	}

}

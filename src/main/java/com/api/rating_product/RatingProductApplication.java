package com.api.rating_product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("file:.env")
public class RatingProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(RatingProductApplication.class, args);
	}

}

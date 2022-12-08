package com.spring.app.allokal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URISyntaxException;


@SpringBootApplication
public class AllokalApplication {


	public static void main(String[] args) throws URISyntaxException, IOException {
		SpringApplication.run(AllokalApplication.class, args);

	}

}

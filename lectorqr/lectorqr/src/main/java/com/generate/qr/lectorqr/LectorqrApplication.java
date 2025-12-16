package com.generate.qr.lectorqr;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class LectorqrApplication  {

	private static ConfigurableApplicationContext context;

	public static void main(String[] args) {

		SpringApplication.run(LectorqrApplication.class, args);

	}


}

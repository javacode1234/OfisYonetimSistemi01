package com.ofisyonetimsistemi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.ofisyonetimsistemi")
public class OfisYonetimSistemiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OfisYonetimSistemiApplication.class, args);
	}
	
	
	

}

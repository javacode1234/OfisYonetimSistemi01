package com.ofisyonetimsistemi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.ofisyonetimsistemi"})
public class OfisYonetimSistemiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OfisYonetimSistemiApplication.class, args);
	}
	
	
	

}

package com.improveusa.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication(scanBasePackages={"com.improveusa"})
@EntityScan("com.improveusa.entity")
public class ComparisonApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComparisonApplication.class, args);
	}
}

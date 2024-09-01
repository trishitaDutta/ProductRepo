package com.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class SpringDataJpaProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaProductApplication.class, args);
	}

}



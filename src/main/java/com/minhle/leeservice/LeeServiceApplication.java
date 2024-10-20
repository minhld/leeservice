package com.minhle.leeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.schema.client.EnableSchemaRegistryClient;

@SpringBootApplication
public class LeeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeeServiceApplication.class, args);
	}

}

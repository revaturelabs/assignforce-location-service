package com.revature.assignforce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LocationServiceApplication {

	public static void main(String[] args) throws Exception {
		new SpringApplicationBuilder(LocationServiceApplication.class).run(args);

//		SpringApplication.run(LocationServiceApplication.class, args);
	}

}

package com.revature.assignforce;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableDiscoveryClient
@SpringBootApplication
@EnableWebSecurity
public class LocationServiceApplication {

	public static void main(String[] args) throws Exception {
		new SpringApplicationBuilder(LocationServiceApplication.class).run(args);
	}

}

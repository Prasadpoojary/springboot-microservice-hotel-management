package com.tcs.servicedisc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServiceDiscApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceDiscApplication.class, args);
	}

}

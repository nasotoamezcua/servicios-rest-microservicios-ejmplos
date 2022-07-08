package com.tecnonessystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.tecnonessystem.commons.entity"})
public class MicroservicioProductoV1Application {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioProductoV1Application.class, args);
	}

}

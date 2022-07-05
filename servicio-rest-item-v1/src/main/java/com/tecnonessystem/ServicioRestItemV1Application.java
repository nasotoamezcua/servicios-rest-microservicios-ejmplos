package com.tecnonessystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@RibbonClient("servicio-productos")
@EnableCircuitBreaker
@EnableFeignClients
@SpringBootApplication
public class ServicioRestItemV1Application {

	public static void main(String[] args) {
		SpringApplication.run(ServicioRestItemV1Application.class, args);
	}

}

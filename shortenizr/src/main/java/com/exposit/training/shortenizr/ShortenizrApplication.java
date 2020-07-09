package com.exposit.training.shortenizr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.exposit.training.shortenizr")
@EnableDiscoveryClient
public class ShortenizrApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShortenizrApplication.class, args);
	}

}

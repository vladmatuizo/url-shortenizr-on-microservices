package com.exposit.training.linksstatisticservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LinksStatisticServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LinksStatisticServiceApplication.class, args);
    }

}

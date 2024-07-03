package com.target.shipments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = "com.target.shipments.repository")
public class ShipmentApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShipmentApplication.class, args);
    }
}

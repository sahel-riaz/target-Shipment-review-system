package com.target.shipments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.target.shipments.repository.jpa")
@EnableElasticsearchRepositories(basePackages = "com.target.shipments.repository.elasticsearch")
public class ShipmentApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShipmentApplication.class, args);
    }
}

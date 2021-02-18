package de.hbrs.tlohm12s.servicefactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ServiceFactoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceFactoryApplication.class, args);
    }

}

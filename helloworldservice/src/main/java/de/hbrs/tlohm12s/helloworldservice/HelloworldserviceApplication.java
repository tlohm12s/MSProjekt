package de.hbrs.tlohm12s.helloworldservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class HelloworldserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloworldserviceApplication.class, args);
    }

}

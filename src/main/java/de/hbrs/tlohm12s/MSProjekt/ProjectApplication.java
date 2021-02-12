package de.hbrs.tlohm12s.MSProjekt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


//https://spring.io/guides/gs/service-registration-and-discovery/
//https://spring.io/guides/gs/gateway/

@EnableEurekaServer
@SpringBootApplication
public class ProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }

}

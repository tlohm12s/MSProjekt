package de.hbrs.tlohm12s.MSProjekt.services;

import com.netflix.discovery.shared.Applications;
import com.netflix.eureka.EurekaServerContextHolder;
import com.netflix.eureka.registry.PeerAwareInstanceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.List;

//https://spring.io/blog/2015/01/20/microservice-registration-and-discovery-with-spring-cloud-and-netflix-s-eureka

@RestController
public class GatewayService {

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/service/{applicationName}")
    public List<String> serviceInstancesByApplicationName(
            @PathVariable String applicationName) {

        PeerAwareInstanceRegistry registry = EurekaServerContextHolder.getInstance().getServerContext().getRegistry();
        Applications applications = registry.getApplications();

        applications.getRegisteredApplications().forEach((registeredApplication) -> {
            registeredApplication.getInstances().forEach((instance) -> {
                System.out.println(instance.getAppName() + " (" + instance.getInstanceId() + ") : " );
            });
        });
        return this.discoveryClient.getServices();
    }
/*
    @RequestMapping("/service/{applicationName}")
    public List<ServiceInstance> redirectService(
            @PathVariable String applicationName) {
        return this.discoveryClient.getInstances(applicationName);
    }

    @RequestMapping("/service/start/{serviceType}")
    public void startService(@PathVariable String applicationName, @PathVariable String serviceType) {
        ServiceFactory serviceFactory = new ServiceFactory();
        serviceFactory.createService(serviceType);
    }

    @RequestMapping("/service/stop/{applicationName}")
    public void stopService (
            @PathVariable String applicationName) {

    }
*/
}

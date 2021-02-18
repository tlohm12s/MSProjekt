package de.hbrs.tlohm12s.MSProjekt;

import com.netflix.discovery.shared.Applications;
import com.netflix.eureka.EurekaServerContextHolder;
import com.netflix.eureka.registry.PeerAwareInstanceRegistry;
import de.hbrs.tlohm12s.MSProjekt.exceptions.ServiceNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiscoveryRegisterServiceController {


    //https://stackoverflow.com/questions/42427492/eureka-server-list-all-registered-instances

    /**
     * Returns the Host given by the instanceID from the dynamically generated service.
     * @param instanceID InstanceID of the service
     * @return Host in Format http://localhost:Port/
     */
    @GetMapping("/service/{instanceID}")
    public String getHostByInstanceID(@PathVariable String instanceID) {
        PeerAwareInstanceRegistry registry = EurekaServerContextHolder.getInstance().getServerContext().getRegistry();
        Applications applications = registry.getApplications();

        final String[] host = new String[1];

        applications.getRegisteredApplications().forEach((registeredApplication) -> registeredApplication.getInstances().forEach((instance) -> {
            String currentInstanceID = instance.getInstanceId().split(":")[2];

            if (instanceID.equals(currentInstanceID)) {
                host[0] = instance.getHomePageUrl();
            }

        }));

        if (host[0] == null) {
            throw new ServiceNotFoundException();
        }

        return host[0];
    }

}

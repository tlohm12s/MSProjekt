package de.hbrs.tlohm12s.gatewayservice;

import com.fasterxml.jackson.databind.JsonNode;
import com.netflix.discovery.EurekaClient;
import de.hbrs.tlohm12s.gatewayservice.exceptions.ServiceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class GatewayServiceController {

    @Qualifier("eurekaClient")
    @Autowired
    EurekaClient eurekaClient;

    /**
     * Empty Statement for the Interceptor to work
     * @param instanceID InstanceID of the service
     */
    @RequestMapping("/service/{instanceID}/**")
    public void redirect(@PathVariable String instanceID) { }

    /**
     * Starts the service with the given service Type
     * @param serviceType Service Type (HELLO_WORLD_SERVICE)
     * @return Creation Message
     */
    @PostMapping(value = "/start/{serviceType}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String startService(@PathVariable String serviceType) {
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8080/service/" + serviceType;
        ResponseEntity<String> response;
        try {
            response = restTemplate.postForEntity(url, null, String.class);
        } catch(RestClientException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return response.getBody();
    }

    /**
     * Stops the service with the given instanceID
     * @param instanceID Service InstanceID
     * @return Message returned from /actuator/shutdown
     */
    @GetMapping("/stop/{instanceID}")
    public String stopService(@PathVariable String instanceID) {
        RestTemplate restTemplate = new RestTemplate();
        String url = getHostByInstanceID(instanceID)+"actuator/shutdown";

        if (url.equals("")) {
            throw new ServiceNotFoundException();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        return restTemplate.postForEntity(url, entity, String.class).getBody();
    }

    /**
     * Returns the Host given by the instanceID from the dynamically generated service.
     * @return Host in Format http://localhost:Port/
     * **/
    String getHostByInstanceID(String instanceID) {
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8761/service/" + instanceID;
        return restTemplate.getForObject(url, String.class);
    }

}

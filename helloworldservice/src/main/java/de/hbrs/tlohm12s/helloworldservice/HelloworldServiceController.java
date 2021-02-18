package de.hbrs.tlohm12s.helloworldservice;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloworldServiceController {

    @Qualifier("eurekaClient")
    @Autowired
    EurekaClient eurekaClient;

    @GetMapping("/helloworld")
    public String greeting() {
        return "Hello World! I'm instance " +
                eurekaClient.getApplicationInfoManager().
                getInfo().getInstanceId();
    }

}

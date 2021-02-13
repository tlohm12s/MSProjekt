package de.hbrs.tlohm12s.helloworldservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloworldserviceController {

    @GetMapping("/")
    public String greeting() {
        return "Hello World";
    }

}

package de.hbrs.tlohm12s.helloworldservice;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloworldserviceController {

    @RequestMapping("/")
    public String get() {
        return "Hello World";
    }

}

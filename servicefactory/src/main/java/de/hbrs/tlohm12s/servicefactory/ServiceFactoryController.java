package de.hbrs.tlohm12s.servicefactory;

import de.hbrs.tlohm12s.servicefactory.exceptions.ServiceRuntimeException;
import de.hbrs.tlohm12s.servicefactory.exceptions.ServiceTypeNotFoundException;
import de.hbrs.tlohm12s.servicefactory.factory.AbstractServiceFactory;
import de.hbrs.tlohm12s.servicefactory.generatableServices.GeneratableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.io.IOException;

@RestController
public class ServiceFactoryController {

    @Autowired
    AbstractServiceFactory serviceFactory;

    /**
     * Generates a service and starts it with the Service Factory, if possible
     * @param serviceType
     * @return JsonObject { instanceID : { string : abc123 }, message : { string : ... }}
     */
    @PostMapping("/service/{serviceType}")
    public JsonObject generateService(@PathVariable String serviceType) {
        GeneratableService generatableService = serviceFactory.createService(serviceType);

        //If the serviceType is not found
        if (generatableService == null)
            throw new ServiceTypeNotFoundException();

        try {
            generatableService.run();
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceRuntimeException();
        }

        return getJSONObject(generatableService.getType(), generatableService.getInstanceID());
    }

    /**
     * Creates a response Object
     * @param type
     * @param id
     * @return
     */
    public JsonObject getJSONObject(String type, String id) {

        return Json.createObjectBuilder()
                .add("message", "Service generated!")
                .add("type", type)
                .add("id", id).build();

    }

}

package de.hbrs.tlohm12s.servicefactory.factory;

import de.hbrs.tlohm12s.servicefactory.generatableServices.GeneratableService;
import de.hbrs.tlohm12s.servicefactory.generatableServices.HelloWorldService;
import org.springframework.stereotype.Component;

@Component
public class ServiceFactory extends AbstractServiceFactory{

    @Override
    public GeneratableService createService(String type) {
        GeneratableService generatableService = null;

        if (HelloWorldService.SERVICE_NAME.equals(type)) {
            generatableService = new HelloWorldService();
        }

        return generatableService;
    }
}

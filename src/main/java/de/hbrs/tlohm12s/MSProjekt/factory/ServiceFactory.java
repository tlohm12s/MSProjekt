package de.hbrs.tlohm12s.MSProjekt.factory;

import de.hbrs.tlohm12s.MSProjekt.services.generatable.GeneratableService;
import de.hbrs.tlohm12s.MSProjekt.services.generatable.HelloWorldService;

public class ServiceFactory extends AbstractServiceFactory{

    public final static String HELLO_WORLD_SERVICE = "HELLO_WORLD_SERVICE";

    @Override
    public GeneratableService createService(String type) {
        GeneratableService generatableService = null;

        switch (type) {
            case HELLO_WORLD_SERVICE:
                generatableService = new HelloWorldService();
                break;
        }

        return generatableService;
    }
}

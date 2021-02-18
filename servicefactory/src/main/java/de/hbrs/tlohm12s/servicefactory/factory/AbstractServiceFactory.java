package de.hbrs.tlohm12s.servicefactory.factory;

import de.hbrs.tlohm12s.servicefactory.generatableServices.GeneratableService;

public abstract class AbstractServiceFactory {
    /**
     * Creates service depending on Type
     * @param type (HELLO_WORLD_SERVICE)
     * @return Service Instance
     */
    public abstract GeneratableService createService(String type);

}

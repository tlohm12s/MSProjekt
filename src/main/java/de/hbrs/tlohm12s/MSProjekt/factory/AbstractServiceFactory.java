package de.hbrs.tlohm12s.MSProjekt.factory;

import de.hbrs.tlohm12s.MSProjekt.services.generatable.GeneratableService;

abstract class AbstractServiceFactory {

    abstract GeneratableService createService(String type);

}

package de.hbrs.tlohm12s.MSProjekt.services.generatable;

public class HelloWorldService extends GeneratableService {

    @Override
    public String doSomething() {
        return "Hello World, I'm Instance " + ID;
    }

}

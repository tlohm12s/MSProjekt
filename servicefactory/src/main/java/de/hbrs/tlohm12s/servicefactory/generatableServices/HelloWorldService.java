package de.hbrs.tlohm12s.servicefactory.generatableServices;

public class HelloWorldService extends GeneratableService {

    private final static String JAR_FILE = "helloworldservice-0.0.1-SNAPSHOT.jar";
    public final static String SERVICE_NAME = "HELLO_WORLD_SERVICE";

    public HelloWorldService() {
        super(JAR_FILE, SERVICE_NAME);
    }

}

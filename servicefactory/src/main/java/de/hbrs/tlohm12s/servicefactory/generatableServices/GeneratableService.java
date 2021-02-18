package de.hbrs.tlohm12s.servicefactory.generatableServices;

import de.hbrs.tlohm12s.servicefactory.exceptions.ServiceRuntimeException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

public abstract class GeneratableService {

    private String uniqueID;
    private String program;
    private String serviceName;

    public GeneratableService(String jar, String serviceName) {
        program = jar;
        this.serviceName = serviceName;
    }

    /**
     * Starts a instance of the service
     * @return Service Instance ID
     * @throws IOException in case File is not found
     */
    public String run() throws IOException {

        uniqueID = UUID.randomUUID().toString();

        if (!Files.exists(Path.of(program))) throw new IOException();

        //Passes the instanceID to the Service on startup
        String instanceIDArgument =
                "--eureka.instance.instance-id="
                +"${spring.cloud.client.hostname}:${spring.application.name}:${spring.application.instance_id:"+uniqueID+"}";

        //Starts a new java process with given arguments
        ProcessBuilder processBuilder = new ProcessBuilder("java", "-jar", program, instanceIDArgument);

        Process process;

        try {
            process = processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceRuntimeException();
        }

        //Starts a thread running parallel to the program
        Thread thread = new Thread(() -> {
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            try {
                while ((line = input.readLine()) != null)
                    System.out.println(line);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        thread.start();

        return uniqueID;
    }

    public String getInstanceID() {
        return uniqueID;
    }

    public String getType() {
        return serviceName;
    }

}

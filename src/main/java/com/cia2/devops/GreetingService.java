package com.cia2.devops;

import org.springframework.stereotype.Service;

/**
 * Business logic of the app.
 * DEMO: to show a change going through the pipeline, edit APP_VERSION
 * (and the expected value in GreetingServiceTest) and push.
 */
@Service
public class GreetingService {

    public static final String APP_VERSION = "Version 1";

    public String getVersion() {
        return APP_VERSION;
    }

    public String getMessage() {
        return "Welcome to the CIA 2 DevOps App - " + APP_VERSION;
    }
}

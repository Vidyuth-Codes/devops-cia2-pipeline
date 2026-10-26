package com.cia2.devops;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Pure unit tests: no Spring, no server - just the Java class. */
class GreetingServiceTest {

    private final GreetingService service = new GreetingService();

    @Test
    void versionIsTheExpectedRelease() {
        // DEMO: update this together with APP_VERSION, otherwise the pipeline stops here
        assertEquals("Version 2", service.getVersion());
    }

    @Test
    void messageContainsVersion() {
        assertTrue(service.getMessage().contains(service.getVersion()));
    }

    @Test
    void messageHasWelcomeText() {
        assertTrue(service.getMessage().startsWith("Welcome"));
    }
}

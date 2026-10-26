package com.cia2.devops;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** Entry point: starts the embedded web server on port 8080. */
@SpringBootApplication
public class DevopsApplication {
    public static void main(String[] args) {
        SpringApplication.run(DevopsApplication.class, args);
    }
}

package com.cia2.devops;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/** Web endpoints of the app. */
@RestController
public class HomeController {

    private final GreetingService greetingService;

    public HomeController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    /** Home page shown in the browser. */
    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String home() {
        String host = System.getenv().getOrDefault("HOSTNAME", "local");
        return "<html><head><title>CIA 2 DevOps App</title></head>"
             + "<body style='font-family:Arial;text-align:center;margin-top:80px'>"
             + "<h1>" + greetingService.getMessage() + "</h1>"
             + "<p>Built with Maven, packaged with Docker, deployed to Azure Kubernetes Service by GitHub Actions + Ansible.</p>"
             + "<p style='color:gray'>Served by pod: " + host + "</p>"
             + "</body></html>";
    }

    /** Plain-text version, used by the pipeline's verify stage. */
    @GetMapping("/version")
    public String version() {
        return greetingService.getVersion();
    }

    /** Health check used by Kubernetes probes. */
    @GetMapping("/health")
    public String health() {
        return "UP";
    }
}

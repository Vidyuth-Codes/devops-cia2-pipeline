package com.cia2.devops;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/** Web-layer tests: call the HTTP endpoints without starting a real server. */
@WebMvcTest(HomeController.class)
@Import(GreetingService.class)
class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void homePageShowsWelcomeMessage() throws Exception {
        mockMvc.perform(get("/"))
               .andExpect(status().isOk())
               .andExpect(content().string(containsString("Welcome to the CIA 2 DevOps App")));
    }

    @Test
    void versionEndpointReturnsVersion() throws Exception {
        mockMvc.perform(get("/version"))
               .andExpect(status().isOk())
               .andExpect(content().string(GreetingService.APP_VERSION));
    }

    @Test
    void healthEndpointReturnsUp() throws Exception {
        mockMvc.perform(get("/health"))
               .andExpect(status().isOk())
               .andExpect(content().string("UP"));
    }
}

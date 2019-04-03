package com.epam.edu.htm.controller.config;

import com.epam.edu.htm.core.service.impl.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.epam.edu.htm.controller")
public class UserControllerTestConfig {

    @Bean
    public UserService userService() {
        return Mockito.mock(UserService.class);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return Mockito.mock(ObjectMapper.class);
    }
}

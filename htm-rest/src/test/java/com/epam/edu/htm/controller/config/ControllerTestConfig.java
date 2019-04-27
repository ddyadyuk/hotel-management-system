package com.epam.edu.htm.controller.config;

import com.epam.edu.htm.core.service.impl.AddressService;
import com.epam.edu.htm.core.service.impl.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

@Configuration
@ComponentScan(basePackages = "com.epam.edu.htm.controller")
public class ControllerTestConfig {

    @Bean
    public UserService userService() {
        return mock(UserService.class);
    }

    @Bean
    public AddressService addressService() {
        return mock(AddressService.class);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return mock(ObjectMapper.class);
    }
}

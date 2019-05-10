package com.epam.edu.htm.client.config;

import com.epam.edu.htm.client.dao.impl.RestUserDao;
import com.epam.edu.htm.client.service.impl.UserRestService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import static org.mockito.Mockito.mock;

@Component
@ComponentScan("com.epam.edu.htm.client")
public class RestTestConfig {

    @Bean
    public RestUserDao restUserDao() {
        return mock(RestUserDao.class);
    }

    @Bean
    public UserRestService userRestService() {
        return mock(UserRestService.class);
    }
}

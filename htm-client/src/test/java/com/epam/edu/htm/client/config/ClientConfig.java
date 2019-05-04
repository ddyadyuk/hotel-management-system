package com.epam.edu.htm.client.config;

import com.epam.edu.htm.client.dao.UserRestDao;
import com.epam.edu.htm.client.dao.impl.RestUserDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableTransactionManagement
public class ClientConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public UserRestDao userDao() {
        return new RestUserDao(restTemplate());
    }
}

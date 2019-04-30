package com.epam.edu.htm.dao.config;

import com.epam.edu.htm.dao.mapper.ContactMapper;
import com.epam.edu.htm.dao.mapper.UserRowMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

@Configuration@PropertySource("classpath:db/sql.properties")
public class DaoConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer appPostgres() {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer =
                new PropertySourcesPlaceholderConfigurer();
        propertySourcesPlaceholderConfigurer.setLocations(new ClassPathResource(
                ""));
        return propertySourcesPlaceholderConfigurer;
    }

    @Bean
    public static UserRowMapper userRowMapper() {
        return new UserRowMapper();
    }

    @Bean
    public static ContactMapper contactMapper() {
        return new ContactMapper();
    }
}

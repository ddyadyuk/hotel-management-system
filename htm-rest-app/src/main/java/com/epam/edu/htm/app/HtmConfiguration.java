package com.epam.edu.htm.app;

import com.epam.edu.htm.core.service.impl.UserService;
import com.epam.edu.htm.dao.JdbcUserDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "com.epam.edu.htm")
public class HtmConfiguration {
    @Value("createDatabase.sql")
    private static String init;
    @Value("initialization.sql")
    private static String setup;

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver ");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/hoteluser");
        dataSource.setUsername("hoteluser");
        dataSource.setPassword("hotelUser");
        Resource initSchema = new ClassPathResource(init);
        Resource setupSchema = new ClassPathResource(setup);
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.addScripts(initSchema,setupSchema);
        DatabasePopulatorUtils.execute(databasePopulator, dataSource);
        return dataSource;
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource());
    }

    @Bean
    public JdbcUserDao jdbcUserDao() {
        return new JdbcUserDao(namedParameterJdbcTemplate());
    }
    @Bean
    public UserService userService() {
        return new UserService(jdbcUserDao());
    }
}

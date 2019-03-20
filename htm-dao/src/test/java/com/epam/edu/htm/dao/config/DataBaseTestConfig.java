package com.epam.edu.htm.dao.config;

import com.epam.edu.htm.core.dao.UserDao;
import com.epam.edu.htm.dao.JdbcUserDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@PropertySource({"classpath:sql.properties"})
@EnableTransactionManagement
public class DataBaseTestConfig {

    @Bean
    public DataSource embeddedDatabase(){
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();

        return builder
                .setType(EmbeddedDatabaseType.H2)
                .addScript("createDatabase.sql")
                .addScript("initialization.sql")
                .build();
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(embeddedDatabase());
    }

    @Bean
    public PlatformTransactionManager transactionManager(){
        return new DataSourceTransactionManager(embeddedDatabase());
    }

    @Bean
    public UserDao userDao() {
        return new JdbcUserDao(namedParameterJdbcTemplate());
    }
}

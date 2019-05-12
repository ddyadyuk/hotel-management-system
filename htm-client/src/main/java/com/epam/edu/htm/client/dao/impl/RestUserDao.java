package com.epam.edu.htm.client.dao.impl;

import com.epam.edu.htm.client.dao.UserRestDao;
import com.epam.edu.htm.client.dto.UserRestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class RestUserDao implements UserRestDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestUserDao.class);

    //    to properties
    private static final String USER_WITHOUT_PARAMETERS = "http://localhost:8000/htm_rest_app_war_exploded/user/";
    private static final String USER_WITH_PARAMETERS = "http://localhost:8000/htm_rest_app_war_exploded/user/{id}";
    private static final String RESOURCE_NOT_FOUND = "Resource cannot be found";

    private RestTemplate restTemplate;

    @Autowired
    public RestUserDao(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Optional<Long> addUser(UserRestDto user) {
        LOGGER.debug("addUser() rest method has started with user: {}", user);

        Long userId;
        try {
            userId = restTemplate.postForObject(USER_WITHOUT_PARAMETERS, user, Long.class);
        } catch (HttpClientErrorException.NotFound e) {
            LOGGER.debug(RESOURCE_NOT_FOUND);
            return Optional.empty();
        }

        return Optional.of(userId);
    }

    @Override
    public Boolean deleteUser(Long id) {
        LOGGER.debug("deleteUser rest method has started with user id: {}", id);

        HttpEntity<?> entity = new HttpEntity<>(createHttpHeaders());
        Boolean isUserDeleted;
        try {
            ResponseEntity<Boolean> response = restTemplate
                    .exchange(USER_WITH_PARAMETERS, HttpMethod.DELETE, entity, Boolean.class, id);
            isUserDeleted = response.getBody();
        } catch (HttpClientErrorException.NotFound e) {
            LOGGER.debug(RESOURCE_NOT_FOUND);
            isUserDeleted = false;
        }

        return isUserDeleted;
    }

    @Override
    public List<UserRestDto> findAllUsers() {
        LOGGER.debug("findAllUsers rest method has started");

        UserRestDto[] userRestDtos;

        try {
            userRestDtos = restTemplate.getForObject(USER_WITHOUT_PARAMETERS, UserRestDto[].class);
        } catch (HttpClientErrorException.NotFound e) {
            LOGGER.debug(RESOURCE_NOT_FOUND);
            return Collections.emptyList();
        }

        return Arrays.asList(userRestDtos);
    }

    @Override
    public Optional<UserRestDto> findUserById(Long id) {
        LOGGER.debug("findUserById rest method with userId : {}", id);

        UserRestDto findedUser;

        try {
            findedUser = restTemplate.getForObject(USER_WITH_PARAMETERS, UserRestDto.class, id);
        } catch (HttpClientErrorException.NotFound e) {
            LOGGER.error(RESOURCE_NOT_FOUND);
            return Optional.empty();
        }

        return Optional.of(findedUser);
    }

    @Override
    public Boolean editUser(UserRestDto user) {
        LOGGER.debug("editUser rest method with user: {} & id:{}", user, user.getId());

        HttpEntity<?> entity = new HttpEntity<>(user, createHttpHeaders());
        Boolean isUserEdited;

        try {
            ResponseEntity<Boolean> response = restTemplate
                    .exchange(USER_WITH_PARAMETERS, HttpMethod.PUT, entity, Boolean.class, user.getId());
            isUserEdited = response.getBody();
        } catch (HttpClientErrorException.NotFound e) {
            LOGGER.debug(RESOURCE_NOT_FOUND);
            return false;
        }

        return isUserEdited;
    }

    private HttpHeaders createHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        return httpHeaders;
    }
}

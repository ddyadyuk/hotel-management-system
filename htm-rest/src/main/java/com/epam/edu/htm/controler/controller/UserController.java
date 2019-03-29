package com.epam.edu.htm.controler.controller;

import com.epam.edu.htm.core.service.impl.UserService;
import com.epam.edu.htm.model.User;
import com.epam.edu.htm.model.json.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@Controller
public class UserController {
    private  static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @JsonView(View.UserWithPassword.class)
    public @ResponseBody Long addUser(@RequestBody @Validated User user) {
        LOGGER.debug("addUser() method with user: {}", user);
        return userService.addUser(user).orElseThrow(() -> new NoSuchElementException("No such element exception"));
    }
}

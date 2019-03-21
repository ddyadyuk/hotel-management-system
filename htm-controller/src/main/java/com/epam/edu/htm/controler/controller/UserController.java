package com.epam.edu.htm.controler.controller;

import com.epam.edu.htm.core.service.impl.UserService;
import com.epam.edu.htm.model.User;
import com.epam.edu.htm.model.json.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Controller
public class UserController {
    private  static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    @JsonView(View.UserWithPassword.class)
    public Optional<Long> addUser(@RequestBody User user) {
        LOGGER.debug("addUser() method with user: {}", user);
        return userService.addUser(user);
    }
}

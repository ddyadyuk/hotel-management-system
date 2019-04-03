package com.epam.edu.htm.controler.controller;

import com.epam.edu.htm.controler.dto.UserDto;
import com.epam.edu.htm.core.service.impl.UserService;
import com.epam.edu.htm.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Long addUser(@RequestBody @Valid UserDto userDto) {
        LOGGER.debug("addUser() method with user: {}", userDto);
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        LOGGER.debug("User with copied properties: {}", user);
        return userService.addUser(user).orElseThrow(() -> new NoSuchElementException("No such element exception"));
    }
}

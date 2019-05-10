package com.epam.edu.htm.controler.controller;

import com.epam.edu.htm.controler.dto.UserDto;
import com.epam.edu.htm.controler.mapper.UserMapper;
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
import java.util.ArrayList;
import java.util.List;

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
    public @ResponseBody
    Long addUser(@RequestBody @Valid UserDto userDto) {
        LOGGER.debug("addUser() method with user: {}", userDto);

        User user = new User();
        BeanUtils.copyProperties(userDto, user);

        LOGGER.debug("User with copied properties: {}", user);

        return userService.addUser(user);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    Boolean deleteUser(@PathVariable("id") Long id) {
        LOGGER.debug("deleteUser() method with userId: {}", id);

        return userService.deleteUser(id);
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    List<UserDto> findAllUsers() {
        LOGGER.debug("Find all users");

        List<UserDto> userDtos = new ArrayList<>();
        List<User> users = userService.findAllUsers();

        for(User user : users) {
            UserDto userDto = UserMapper.INSTANCE.userToDto(user);
            userDtos.add(userDto);
        }

        return userDtos;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    UserDto findUserById(@PathVariable("id") Long id) {
        LOGGER.debug("findUserById method with 'user id': {}", id);

        User user = userService.findUserById(id);

        return UserMapper.INSTANCE.userToDto(user);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    Boolean editUser(@PathVariable("id") Long id, @Valid @RequestBody UserDto userDto) {
        LOGGER.debug("editUser(): user with id: {} and name: {} will be edited", id, userDto.getName());

        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        user.setUserId(id);

        LOGGER.debug("User with copied properties: {}", user);

        return userService.editUser(user);
    }

}
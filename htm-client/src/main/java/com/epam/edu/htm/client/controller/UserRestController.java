package com.epam.edu.htm.client.controller;

import com.epam.edu.htm.client.dto.UserRestDto;
import com.epam.edu.htm.client.service.impl.UserRestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserRestController.class);

    private UserRestService userRestService;

    @Autowired
    public UserRestController(UserRestService userRestService) {
        this.userRestService = userRestService;
    }

    @GetMapping("/users/")
    public String getAllUsers(Model model) {
        LOGGER.debug("getAllUsers rest method.");

        List<UserRestDto> users = userRestService.findAllUsers();
        model.addAttribute("allUsers", users);
        return "getAllUsers";
    }

    @GetMapping("/users/{id}")
    public String findUserById(@PathVariable("id") Long id, Model model) {
        LOGGER.debug("findUserById method with user id : {}.", id);

        UserRestDto user = userRestService.findUserById(id);
        model.addAttribute("user", user);

        return "user";
    }

    @GetMapping("/users/add")
    public String addUserPage(Model model) {
        LOGGER.debug("addUserPage()");
        model.addAttribute("user", new UserRestDto());

        return "addUser";
    }

    @PostMapping("/users/add")
    public String addUser(@ModelAttribute @Validated UserRestDto userRestDto) {
        LOGGER.debug("addUser rest method.");
        Long id = userRestService.addUser(userRestDto);
        userRestDto.setId(id);
        LOGGER.debug("User with id: {} was added.", id);

        return "redirect:/users/";
    }

    @GetMapping("/users/{id}/edit")
    public String editUserPage(@PathVariable("id") Long id,  Model model) {
        LOGGER.debug("User with id:{} will be edited", id);
        UserRestDto user = userRestService.findUserById(id);
        model.addAttribute("user", user);

        return "editUser";
    }

    @PostMapping("/users/{id}/edit")
    public String editUser(@PathVariable("id") Long id, @ModelAttribute UserRestDto user) {
        LOGGER.debug("editUser method, user with id : {} will be edited.", user.getId());
        userRestService.editUser(user);

        return "redirect:/users/";
    }

    @GetMapping("/users/{id}/delete")
    public String deleteUser(@PathVariable("id") Long id ) {
        LOGGER.debug("deleteUser rest method, user with id: {} will be deleted.", id);

        userRestService.deleteUser(id);
        LOGGER.debug("User with id: {} was deleted", id);

        return "redirect:/users/";
    }
}

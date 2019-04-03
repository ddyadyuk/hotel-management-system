package com.epam.edu.htm.controller;

import com.epam.edu.htm.controler.controller.UserController;
import com.epam.edu.htm.controler.errorhandler.CustomRestExceptionHandler;
import com.epam.edu.htm.controller.config.UserControllerTestConfig;
import com.epam.edu.htm.core.service.impl.UserService;
import com.epam.edu.htm.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.InputStream;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {UserControllerTestConfig.class})
@WebAppConfiguration
public class UserControllerTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserControllerTest.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;


    @Before
    public void setup() throws JsonProcessingException {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new UserController(userService))
                .setControllerAdvice(CustomRestExceptionHandler.class).build();
    }

    @Test
    public void testAddUser_BodyIsAbsent_BadRequest() throws Exception {
        this.mockMvc.perform(post("/user")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(""))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testAddUser_UrlIsNotValid_NotFound() throws Exception {

        this.mockMvc.perform(post("/")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(""))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testAddUser_UserPasswordIsNull_BadRequest() throws Exception {

        User user = new User();
        user.setUserType("user");
        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(user);

        this.mockMvc.perform(post("/user")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testAddUser_UserUserTypeIsNull_BadRequest() throws Exception {

        User user = new User();
        user.setPassword("password");
        objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(user);

        this.mockMvc.perform(post("/user")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testAddUser_MediaTypeIncorrect_NotValid() throws Exception {

        User user = new User();
        user.setPassword("password");
        objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(user);

        when(userService.addUser(any(User.class))).thenReturn(Optional.of(1L));

        this.mockMvc.perform(post("/user")
                .accept(MediaType.TEXT_HTML)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andDo(print())
                .andExpect(status().isNotAcceptable());
    }

    @Test
    public void testAddUser_UserIsCorrect_IsOK() throws Exception {

        User user = new User();
        user.setPassword("abc");
        user.setUserType("user");
        objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(user);

        when(userService.addUser(any(User.class))).thenReturn(Optional.of(1L));

        this.mockMvc.perform(post("/user")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string(String.valueOf((Long) 1L)));

        verify(userService, times(1)).addUser(any(User.class));
    }

    @Test
    public void testAddUser_responseIsNotOk_fail() throws Exception {
        User user = null;
        objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(user);

        when(userService.addUser(any(User.class))).thenReturn(Optional.empty());

        this.mockMvc.perform(post("/user")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andDo(print())
                .andExpect(status().isBadRequest());

        verifyZeroInteractions(userService);
    }

}

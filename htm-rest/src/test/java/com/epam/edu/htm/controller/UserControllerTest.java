package com.epam.edu.htm.controller;

import com.epam.edu.htm.controler.controller.UserController;
import com.epam.edu.htm.controler.errorhandler.CustomRestExceptionHandler;
import com.epam.edu.htm.controller.config.UserControllerTestConfig;
import com.epam.edu.htm.core.service.impl.UserService;
import com.epam.edu.htm.model.Address;
import com.epam.edu.htm.model.Contact;
import com.epam.edu.htm.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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

    private static final Long EXPECTED_ID = 1L;

    @Autowired
    private WebApplicationContext webApplicationContext;

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
    public void testAddUser_responseIsOK_success() throws Exception {
        User user = createTestUser();
        objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(user);

        when(userService.addUser(any(User.class))).thenReturn(Optional.of(EXPECTED_ID));
        this.mockMvc.perform(post("/")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string(String.valueOf(EXPECTED_ID)));

        verify(userService, times(1)).addUser(any(User.class));
    }

    @Test
    public void testAddUser_responseIsNotOk_fail() throws Exception {
        User user = null;
        objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(user);

        when(userService.addUser(any(User.class))).thenReturn(Optional.empty());
        this.mockMvc.perform(post("/")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andDo(print())
                .andExpect(status().isBadRequest());

        verifyZeroInteractions(userService);
    }

    private User createTestUser() {
        User user = new User();
        user.setPassword("abc");
        user.setUserType("user");
        return user;
    }

}

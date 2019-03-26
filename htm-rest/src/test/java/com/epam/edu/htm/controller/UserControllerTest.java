package com.epam.edu.htm.controller;

import com.epam.edu.htm.controler.controller.UserController;
import com.epam.edu.htm.core.service.impl.UserService;
import com.epam.edu.htm.model.Address;
import com.epam.edu.htm.model.Contact;
import com.epam.edu.htm.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserControllerTest.class);

    private static final Long EXPECTED_ID = 1L;

    private MockMvc mockMvc;
    @Mock
    private UserService userService;
    private ObjectMapper objectMapper;

    @Before
    public void setup() throws JsonProcessingException {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(new UserController(userService)).build();
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

    private User createTestUser() {
        User user = new User();
        user.setPassword("abc");
        user.setContact(new Contact());
        user.setAddress(new Address());
        user.setUserType("user");
        return user;
    }

}

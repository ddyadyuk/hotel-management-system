package com.epam.edu.htm.client;

import com.epam.edu.htm.client.config.RestTestConfig;
import com.epam.edu.htm.client.controller.UserRestController;
import com.epam.edu.htm.client.controller.errorhandler.CustomRestExceptionHandler;
import com.epam.edu.htm.client.dto.UserRestDto;
import com.epam.edu.htm.client.service.impl.UserRestService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RestTestConfig.class)
public class RestControllerTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestControllerTest.class);

    @Autowired
    private UserRestService userRestService;

    private UserRestDto userRestDto;
    private List<UserRestDto> userRestDtoList;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setSuffix(".html");
        viewResolver.setPrefix("/WEB-INF/templates/");

        userRestDto = new UserRestDto("password", "Yuri", "user");
        userRestDtoList = new ArrayList<>();
        userRestDtoList.add(userRestDto);
        userRestDtoList.add(userRestDto);
        userRestDtoList.add(userRestDto);

        mockMvc = MockMvcBuilders.standaloneSetup(new UserRestController(userRestService))
                .setControllerAdvice(CustomRestExceptionHandler.class)
                .setViewResolvers(viewResolver).build();
    }

    @After
    public void tearDown() {
        reset(userRestService);

    }

    @Test
    public void testGetAllUsers_AllUsersIsPresent_IsOk() throws Exception {
        when(userRestService.findAllUsers()).thenReturn(userRestDtoList);
        mockMvc.perform(get("/users/"))
                .andExpect(status().isOk())
                .andExpect(view().name("getAllUsers"))
                .andExpect(forwardedUrl("/WEB-INF/templates/getAllUsers.html"))
                .andExpect(model().attributeExists("allUsers"));

        verify(userRestService).findAllUsers();
    }

    @Test
    public void testGetUserById_IdIsCorrect_IsOK() throws Exception {
        when(userRestService.findUserById(1L)).thenReturn(userRestDto);
        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("user"))
                .andExpect(forwardedUrl("/WEB-INF/templates/user.html"))
                .andExpect(model().attributeExists("user"));

        verify(userRestService).findUserById(1L);
    }

    @Test
    public void testGetUserById_IdIsIncorrect_IsBadRequest() throws Exception {
        when(userRestService.findUserById(-1L)).thenThrow(IllegalArgumentException.class);
        mockMvc.perform(get("/users/-1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testAddUser_UserIsCorrect_IsFound() throws Exception {
        when(userRestService.addUser(userRestDto)).thenReturn(1L);
        mockMvc.perform(post("/users/add")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/users/"))
                .andExpect(model().attributeExists("userRestDto"));

        verify(userRestService).addUser(any(UserRestDto.class));
    }

    @Test
    public void testUpdateUser_RequestIsOk_IsFound() throws Exception {
        when(userRestService.editUser(userRestDto)).thenReturn(true);
        mockMvc.perform(post("/users/1/edit")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .param("name", userRestDto.getName())
                .requestAttr("user", userRestDto))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/users/"));

        verify(userRestService).editUser(any(UserRestDto.class));
    }

    @Test
    public void testDeleteUser_RequestIsOk_IsFound() throws Exception {
        when(userRestService.deleteUser(1L)).thenReturn(true);
        mockMvc.perform(get("/users/1/delete")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/users/"));

        verify(userRestService).deleteUser(anyLong());
    }
}

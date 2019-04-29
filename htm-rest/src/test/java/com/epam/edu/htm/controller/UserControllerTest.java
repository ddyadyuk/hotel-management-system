package com.epam.edu.htm.controller;

import com.epam.edu.htm.controler.controller.UserController;
import com.epam.edu.htm.controler.errorhandler.CustomRestExceptionHandler;
import com.epam.edu.htm.controller.config.ControllerTestConfig;
import com.epam.edu.htm.core.service.impl.UserService;
import com.epam.edu.htm.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ControllerTestConfig.class})
@WebAppConfiguration
public class UserControllerTest {

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new UserController(userService))
                .setControllerAdvice(CustomRestExceptionHandler.class).build();
    }

    @Test
    public void testAddUser_BodyIsAbsent_BadRequest() throws Exception {
        this.mockMvc.perform(post("/user")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
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
    public void testAddUser_MediaTypeIsCorrect_NotValid() throws Exception {

        User user = new User();
        user.setPassword("password");
        objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(user);

        when(userService.addUser(any())).thenReturn(1L);

        this.mockMvc.perform(post("/user")
                .accept(MediaType.TEXT_HTML)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andDo(print())
                .andExpect(status().isNotAcceptable());
    }

    @Test
    public void testAddUser_UserIsCorrect_IsOK() throws Exception {

        User user = new User();
        user.setPassword("abc");
        user.setName("Yuri");
        user.setUserType("user");
        objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(user);

        when(userService.addUser(any())).thenReturn(1L);

        this.mockMvc.perform(post("/user")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string(String.valueOf((Long) 1L)));

        verify(userService, times(1)).addUser(any());
    }

    @Test
    public void testAddUser_responseIsNotOk_fail() throws Exception {
        User user = null;
        objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(user);

        when(userService.addUser(any())).thenReturn(null);

        this.mockMvc.perform(post("/user")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testDeleteUser_UrlIsCorrect_IsOk() throws Exception {

        when(userService.deleteUser(anyLong())).thenReturn(true);

        this.mockMvc.perform(delete("/user/1")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk());

        verify(userService, times(1)).deleteUser(anyLong());
    }

    @Test
    public void testDeleteUser_WrongUrl_IsNotFound() throws Exception {

        when(userService.deleteUser(anyLong())).thenReturn(null);

        this.mockMvc.perform(delete("user/1")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteUser_IncorrectMediaType_IsNotAcceptable() throws Exception {

        when(userService.deleteUser(anyLong())).thenReturn(true);

        this.mockMvc.perform(delete("/user/1")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isNotAcceptable());
    }

    @Test
    public void testFindAllUsers_UrlIsCorrect_IsOk() throws Exception {

        this.mockMvc.perform(get("/user/")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    public void testFindAllUsers_IncorrectMediaType_IsBadRequest() throws Exception {

        this.mockMvc.perform(get("/user/users`")
        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isBadRequest());
    }


    @Test
    public void testFindAllUsers_WrongUrl_IsBadRequest() throws Exception {

        this.mockMvc.perform(get("/user/usr"))
                .andExpect(status().isBadRequest());
    }




    @Test
    public void testFindUserById_UserIdIsCorrect_isOk() throws Exception {
        User user = new User();
        user.setPassword("abc");
        user.setName("Ivan");
        user.setUserType("user");
        objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(user);

        this.mockMvc.perform(get("/user/1")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(status().isOk());
    }

    @Test
    public void testEditUser_UserIsOk_Success() throws Exception {

        User user = new User();
        user.setPassword("abc");
        user.setName("Ivan");
        user.setUserType("user");
        objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(user);

        when(userService.editUser(user)).thenReturn(true);

        this.mockMvc.perform(put("/user/1")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(status().isOk());
    }

    @Test
    public void testEditUser_UserNameIsAbsent_isBadRequest() throws Exception {
        User user = new User();
        user.setPassword("abc");
        user.setUserType("user");
        objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(user);

        when(userService.editUser(user)).thenReturn(false);

        this.mockMvc.perform(put("/user/1")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content)).andExpect(status().isBadRequest());
    }

    @Test
    public void testEditUser_ContentTypeIsIncorrect_isNotAcceptable() throws Exception {
        User user = new User();
        user.setPassword("abc");
        user.setName("Kent");
        user.setUserType("user");
        objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(user);

        when(userService.editUser(user)).thenReturn(false);

        this.mockMvc.perform(put("/user/1")
                .accept(MediaType.TEXT_HTML)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content)).andExpect(status().isNotAcceptable());
    }
}

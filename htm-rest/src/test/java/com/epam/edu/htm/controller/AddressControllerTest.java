package com.epam.edu.htm.controller;

import com.epam.edu.htm.controler.controller.AddressController;
import com.epam.edu.htm.controler.errorhandler.CustomRestExceptionHandler;
import com.epam.edu.htm.controller.config.ControllerTestConfig;
import com.epam.edu.htm.core.service.impl.AddressService;
import com.epam.edu.htm.model.Address;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ControllerTestConfig.class})
@WebAppConfiguration
public class AddressControllerTest {
    @Autowired
    private AddressService addressService;
    @Autowired
    private ObjectMapper objectMapper;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new AddressController(addressService))
                .setControllerAdvice(CustomRestExceptionHandler.class).build();
    }

    @Test
    public void testAddAddress_AddressIsPresent_IsCreated() throws Exception {
        Address address = new Address();
        address.setFirsAddress("Europe");
        address.setSecondAddress("Belarus");

        objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(address);

        when(addressService.addAddress(address)).thenReturn(1L);

        mockMvc.perform(post("/address")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(content)).andExpect(status().isCreated());
    }

    @Test
    public void testAddAddress_MediaTypeUnsupported_IsNotAcceptable() throws Exception {
        mockMvc.perform(post("/address")
                .accept(MediaType.TEXT_EVENT_STREAM)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isNotAcceptable());
    }

    @Test
    public void testAddAddress_BodyIsAbsent_IsBadRequest() throws Exception {
        mockMvc.perform(post("/address")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content("")).andExpect(status().isBadRequest());
    }

    @Test
    public void testAddAddress_ResponseIsNotOk_IsBadRequest() throws Exception {
        Address address = null;
        objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(address);

        when(addressService.addAddress(any())).thenReturn(null);

        mockMvc.perform(post("/address")
        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
        .content(content)).andExpect(status().isBadRequest());
    }


}

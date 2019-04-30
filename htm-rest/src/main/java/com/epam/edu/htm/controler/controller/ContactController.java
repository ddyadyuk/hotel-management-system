package com.epam.edu.htm.controler.controller;

import com.epam.edu.htm.controler.dto.ContactDto;
import com.epam.edu.htm.controler.dto.UserDto;
import com.epam.edu.htm.controler.mapper.ContactMapper;
import com.epam.edu.htm.core.service.impl.ContactService;
import com.epam.edu.htm.model.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/contact")
public class ContactController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContactController.class);
    private ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Long addContact(@RequestBody ContactDto contactDto){
        LOGGER.debug("addContact method with parameters: {}", contactDto.toString());

        Contact contact = new Contact();
        BeanUtils.copyProperties(contactDto, contact);

        return contactService.addContact(contact);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Boolean deleteContact(@PathVariable("id") Long id) {
        LOGGER.debug("delete method, deleted contact id is {}", id);

        return contactService.deleteContact(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    List<ContactDto> findAllContacts() {
        LOGGER.debug("findAllContacts method");

        List<ContactDto> contactDtos = new ArrayList<>();
        List<Contact> contacts = contactService.findAllContacts();

        for (Contact contact : contacts) {
            ContactDto contactDto = ContactMapper.INSTANCE.contactToDto(contact);
            contactDtos.add(contactDto);
        }

        return contactDtos;
    }

}

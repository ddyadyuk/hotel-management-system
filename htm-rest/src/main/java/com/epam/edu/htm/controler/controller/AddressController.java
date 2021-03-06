package com.epam.edu.htm.controler.controller;

import com.epam.edu.htm.controler.dto.AddressDto;
import com.epam.edu.htm.controler.mapper.AddressMapper;
import com.epam.edu.htm.core.service.impl.AddressService;
import com.epam.edu.htm.model.Address;
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
@RequestMapping("/address")
public class AddressController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddressController.class);
    private AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    Long addAddress(@RequestBody AddressDto addressDto) {
        LOGGER.debug("addAddress method with parameters: {}", addressDto.toString());
        Address address = new Address();
        BeanUtils.copyProperties(addressDto, address);
        return addressService.addAddress(address);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    Boolean editUser(@PathVariable("id") Long id, @RequestBody AddressDto addressDto) {
        LOGGER.debug("editAddress method with address: {}", addressDto.toString());

        Address address = new Address();
        BeanUtils.copyProperties(addressDto, address);
        address.setAddressId(id);

        return addressService.editAddress(address);
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    List<AddressDto> findAllUsers() {
        LOGGER.debug("Find all users");

        List<AddressDto> addressDtos = new ArrayList<>();
        List<Address> addresses = addressService.findAllAddresses();

        for (Address address : addresses) {
            AddressDto addressDto = AddressMapper.INSTANCE.addressToDto(address);
            addressDtos.add(addressDto);
        }

        return addressDtos;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    AddressDto findAddressById(@PathVariable("id") Long id) {
        LOGGER.debug("findAddressById method with addressId: {}", id);

        Address address = addressService.findAddressById(id);

        return AddressMapper.INSTANCE.addressToDto(address);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    Boolean deleteAddress(@PathVariable("id") Long id) {
        LOGGER.debug("deleteAddress method (address id is {})", id);

        return addressService.deleteAddress(id);
    }
}

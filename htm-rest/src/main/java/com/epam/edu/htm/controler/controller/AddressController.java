package com.epam.edu.htm.controler.controller;

import com.epam.edu.htm.controler.dto.AddressDto;
import com.epam.edu.htm.core.service.impl.AddressService;
import com.epam.edu.htm.model.Address;
import org.omg.PortableInterceptor.LOCATION_FORWARD;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public @ResponseBody Boolean editUser(@PathVariable("id")Long id, @RequestBody AddressDto addressDto) {
        LOGGER.debug("editAddress method with address: {}", addressDto.toString() );

        Address address = new Address();
        BeanUtils.copyProperties(addressDto, address);
        address.setAddressId(id);

        return addressService.editAddress(address);
    }

}

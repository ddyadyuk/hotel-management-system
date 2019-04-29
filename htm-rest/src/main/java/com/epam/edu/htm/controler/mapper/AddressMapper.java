package com.epam.edu.htm.controler.mapper;

import com.epam.edu.htm.controler.dto.AddressDto;
import com.epam.edu.htm.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    AddressDto addressToDto(Address address);
    Address dtoTroAddress(AddressDto addressDto);

}

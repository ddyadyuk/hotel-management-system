package com.epam.edu.htm.controler.mapper;

import com.epam.edu.htm.controler.dto.ContactDto;
import com.epam.edu.htm.model.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ContactMapper {
    ContactMapper INSTANCE = Mappers.getMapper(ContactMapper.class);

    ContactDto contactToDto(Contact contact);
    Contact dtotoContsct(ContactDto contactDto);
}

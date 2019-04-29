package com.epam.edu.htm.controler.mapper;

import com.epam.edu.htm.controler.dto.UserDto;
import com.epam.edu.htm.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE= Mappers.getMapper(UserMapper.class);

    UserDto userToDto(User user);
    User dtoToUser(UserDto userDto);
}


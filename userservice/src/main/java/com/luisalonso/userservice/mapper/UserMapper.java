package com.luisalonso.userservice.mapper;

import org.mapstruct.Mapper;

import com.luisalonso.userservice.dto.UserDto;
import com.luisalonso.userservice.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User entity);

    User toEntity(UserDto dto);

}

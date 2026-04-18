package com.luisalonso.userservice.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.luisalonso.userservice.dto.UserDto;
import com.luisalonso.userservice.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User entity);

    User toEntity(UserDto dto);

    List<UserDto> toDtoList(List<User> entities);

    List<User> toEntityList(List<UserDto> dto);

}

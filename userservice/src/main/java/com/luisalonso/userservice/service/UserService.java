package com.luisalonso.userservice.service;

import org.springframework.stereotype.Service;

import com.luisalonso.userservice.dto.UserDto;
import com.luisalonso.userservice.entity.User;
import com.luisalonso.userservice.mapper.UserMapper;
import com.luisalonso.userservice.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public UserDto getUserByAzureId(String azureId) {

        User user = repository
                .findByAzureId(azureId)
                .orElseThrow(
                        () -> new RuntimeException("User not found"));

        return mapper.toDto(user);
    }

    public UserDto save(UserDto dto) {

        User entity = mapper.toEntity(dto);

        return mapper.toDto(
                repository.save(entity));
    }

}

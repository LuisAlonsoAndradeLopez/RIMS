package com.luisalonso.userservice.service;

import java.util.ArrayList;
import java.util.List;

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

    public List<UserDto> getUsers() {
        return mapper.toDtoList(repository.findAll());
    }
}

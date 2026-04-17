package com.luisalonso.userservice.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import com.luisalonso.userservice.dto.UserDto;
import com.luisalonso.userservice.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService service;

    @GetMapping("/users")
    public List<UserDto> getUsers(
            @AuthenticationPrincipal Jwt jwt) {

        return service.getUsers();
    }
}

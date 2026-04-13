package com.luisalonso.userservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luisalonso.userservice.dto.UserDto;
import com.luisalonso.userservice.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("/me")
    public ResponseEntity<UserDto> me(
            @AuthenticationPrincipal Jwt jwt) {

        String azureId = jwt.getSubject();

        return ResponseEntity.ok(
                service.getUserByAzureId(azureId));
    }

    @PreAuthorize("hasAuthority('GROUP_ADMIN')")
    @GetMapping("/admin")
    public String adminOnly() {

        return "admin access";

    }

}

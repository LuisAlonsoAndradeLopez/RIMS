package com.luisalonso.userservice.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @GetMapping("/users")
    public String getUsers(
            @AuthenticationPrincipal Jwt jwt) {

        String email = jwt.getClaimAsString("preferred_username");

        return "Hello " + email;
    }
}

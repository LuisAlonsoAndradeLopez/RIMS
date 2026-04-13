package com.luisalonso.userservice.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.luisalonso.userservice.dto.UserDto;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService service;

    @Test
    void shouldSaveUser() {

        UserDto dto = new UserDto();

        dto.setName("Luis");

        UserDto saved = service.save(dto);

        assertNotNull(saved);

    }

}

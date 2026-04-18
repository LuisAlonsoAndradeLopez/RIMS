package com.luisalonso.userservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.luisalonso.userservice.dto.UserDto;
import com.luisalonso.userservice.entity.User;
import com.luisalonso.userservice.mapper.UserMapper;
import com.luisalonso.userservice.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository repository;

    @Mock
    private UserMapper mapper;

    @InjectMocks
    private UserService service;

    @Test
    void getUsersTest() {

        User user1 = new User();
        user1.setId(4L);
        user1.setName("Luis Alonso");
        user1.setEmail("luis@example.com");

        User user2 = new User();
        user2.setId(5L);
        user2.setName("Maria Lopez");
        user2.setEmail("maria@example.com");

        UserDto dto1 = new UserDto();
        dto1.setId(4L);
        dto1.setName("Luis Alonso");
        dto1.setEmail("luis@example.com");

        UserDto dto2 = new UserDto();
        dto2.setId(5L);
        dto2.setName("Maria Lopez");
        dto2.setEmail("maria@example.com");

        List<User> users = List.of(user1, user2);
        List<UserDto> dtos = List.of(dto1, dto2);

        when(repository.findAll())
                .thenReturn(users);

        when(mapper.toDtoList(users))
                .thenReturn(dtos);

        List<UserDto> result = service.getUsers();

        assertEquals(2, result.size());
        assertEquals("Luis Alonso", result.get(0).getName());
        assertEquals("Maria Lopez", result.get(1).getName());
    }

}

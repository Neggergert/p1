package com.example.bookexchange.service;

import com.example.bookexchange.dto.CreateUserRequest;
import com.example.bookexchange.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    void createUser() {
        UserDto dto = userService.createUser(new CreateUserRequest("Alice"));
        assertThat(dto.name()).isEqualTo("Alice");
        assertThat(userService.getUsers()).hasSize(1);
    }
}

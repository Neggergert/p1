package com.example.bookexchange.controller;

import com.example.bookexchange.dto.CreateUserRequest;
import com.example.bookexchange.dto.UserDto;
import com.example.bookexchange.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "Users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create user")
    public UserDto create(@Validated @RequestBody CreateUserRequest request) {
        return userService.createUser(request);
    }

    @GetMapping
    @Operation(summary = "List users")
    public List<UserDto> list() {
        return userService.getUsers();
    }
}

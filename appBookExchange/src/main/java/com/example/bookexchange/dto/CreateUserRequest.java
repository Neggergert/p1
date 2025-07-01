package com.example.bookexchange.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateUserRequest(@NotBlank String name) {}

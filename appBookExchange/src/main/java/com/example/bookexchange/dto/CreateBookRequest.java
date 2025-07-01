package com.example.bookexchange.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateBookRequest(@NotBlank String title, @NotBlank String author, @NotNull Long ownerId) {}

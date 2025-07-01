package com.example.bookexchange.dto;

import java.util.List;

public record UserDto(Long id, String name, List<BookDto> books) {}

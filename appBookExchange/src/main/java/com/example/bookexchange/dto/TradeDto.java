package com.example.bookexchange.dto;

import java.time.LocalDateTime;

public record TradeDto(Long id, BookDto offeredBook, BookDto requestedBook, LocalDateTime tradedAt) {}

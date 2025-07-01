package com.example.bookexchange.dto;

import jakarta.validation.constraints.NotNull;

public record CreateTradeRequest(@NotNull Long offeredBookId, @NotNull Long requestedBookId) {}

package com.example.bookexchange.service;

import com.example.bookexchange.dto.BookDto;
import com.example.bookexchange.dto.TradeDto;
import com.example.bookexchange.dto.UserDto;
import com.example.bookexchange.model.Book;
import com.example.bookexchange.model.Trade;
import com.example.bookexchange.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class DtoMapper {
    public static BookDto toDto(Book book) {
        return new BookDto(book.getId(), book.getTitle(), book.getAuthor(),
                book.getOwner() != null ? book.getOwner().getId() : null);
    }

    public static UserDto toDto(User user) {
        List<BookDto> books = user.getBooks().stream().map(DtoMapper::toDto).collect(Collectors.toList());
        return new UserDto(user.getId(), user.getName(), books);
    }

    public static TradeDto toDto(Trade trade) {
        return new TradeDto(trade.getId(), toDto(trade.getOfferedBook()), toDto(trade.getRequestedBook()), trade.getTradedAt());
    }
}

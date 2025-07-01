package com.example.bookexchange.service;

import com.example.bookexchange.dto.CreateBookRequest;
import com.example.bookexchange.dto.CreateUserRequest;
import com.example.bookexchange.dto.BookDto;
import com.example.bookexchange.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class BookServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;

    private Long userId;

    @BeforeEach
    void setUp() {
        UserDto user = userService.createUser(new CreateUserRequest("Bob"));
        userId = user.id();
    }

    @Test
    void addBook() {
        BookDto dto = bookService.addBook(new CreateBookRequest("Title", "Author", userId));
        assertThat(dto.ownerId()).isEqualTo(userId);
        assertThat(bookService.getBooks(userId)).hasSize(1);
    }
}

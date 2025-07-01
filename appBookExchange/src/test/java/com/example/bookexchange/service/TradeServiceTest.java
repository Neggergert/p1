package com.example.bookexchange.service;

import com.example.bookexchange.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class TradeServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;
    @Autowired
    private TradeService tradeService;

    private Long user1Id;
    private Long user2Id;
    private Long book1Id;
    private Long book2Id;

    @BeforeEach
    void setUp() {
        user1Id = userService.createUser(new CreateUserRequest("Tom")).id();
        user2Id = userService.createUser(new CreateUserRequest("Jerry")).id();
        book1Id = bookService.addBook(new CreateBookRequest("Book1", "Author1", user1Id)).id();
        book2Id = bookService.addBook(new CreateBookRequest("Book2", "Author2", user2Id)).id();
    }

    @Test
    void tradeBooks() {
        TradeDto trade = tradeService.makeTrade(new CreateTradeRequest(book1Id, book2Id));
        assertThat(trade.offeredBook().ownerId()).isEqualTo(user2Id);
        assertThat(trade.requestedBook().ownerId()).isEqualTo(user1Id);
    }
}

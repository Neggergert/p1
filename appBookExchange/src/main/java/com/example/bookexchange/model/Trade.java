package com.example.bookexchange.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Trade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Book offeredBook;

    @ManyToOne
    private Book requestedBook;

    private LocalDateTime tradedAt = LocalDateTime.now();

    public Trade() {
    }

    public Trade(Book offeredBook, Book requestedBook) {
        this.offeredBook = offeredBook;
        this.requestedBook = requestedBook;
    }

    public Long getId() {
        return id;
    }

    public Book getOfferedBook() {
        return offeredBook;
    }

    public Book getRequestedBook() {
        return requestedBook;
    }

    public LocalDateTime getTradedAt() {
        return tradedAt;
    }
}

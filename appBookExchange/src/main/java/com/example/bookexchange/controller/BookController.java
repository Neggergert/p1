package com.example.bookexchange.controller;

import com.example.bookexchange.dto.BookDto;
import com.example.bookexchange.dto.CreateBookRequest;
import com.example.bookexchange.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@Tag(name = "Books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add book")
    public BookDto add(@Validated @RequestBody CreateBookRequest request) {
        return bookService.addBook(request);
    }

    @GetMapping
    @Operation(summary = "List books")
    public List<BookDto> list(@RequestParam(required = false) Long ownerId) {
        return bookService.getBooks(ownerId);
    }
}

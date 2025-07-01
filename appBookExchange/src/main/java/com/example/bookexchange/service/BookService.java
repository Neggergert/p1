package com.example.bookexchange.service;

import com.example.bookexchange.dto.BookDto;
import com.example.bookexchange.dto.CreateBookRequest;
import com.example.bookexchange.model.Book;
import com.example.bookexchange.model.User;
import com.example.bookexchange.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookService {
    private final BookRepository bookRepository;
    private final UserService userService;

    public BookService(BookRepository bookRepository, UserService userService) {
        this.bookRepository = bookRepository;
        this.userService = userService;
    }

    public BookDto addBook(CreateBookRequest request) {
        User owner = userService.getUserById(request.ownerId());
        Book book = new Book(request.title(), request.author(), owner);
        bookRepository.save(book);
        owner.getBooks().add(book);
        return DtoMapper.toDto(book);
    }

    @Transactional(readOnly = true)
    public List<BookDto> getBooks(Long ownerId) {
        List<Book> books = ownerId == null ? bookRepository.findAll() : bookRepository.findByOwnerId(ownerId);
        return books.stream().map(DtoMapper::toDto).toList();
    }

    @Transactional(readOnly = true)
    public Book getBook(Long id) {
        return bookRepository.findById(id).orElseThrow();
    }
}

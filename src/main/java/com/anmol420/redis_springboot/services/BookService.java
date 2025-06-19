package com.anmol420.redis_springboot.services;

import com.anmol420.redis_springboot.domain.entities.Book;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookService {

    List<Book> getBooks();
    Book addBook(Book book);
    Optional<Book> getBookById(UUID id);
    Book updateBook(UUID id, Book book);
    void deleteBook(UUID id);

}

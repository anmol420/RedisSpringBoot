package com.anmol420.redis_springboot.services.impl;

import com.anmol420.redis_springboot.domain.entities.Book;
import com.anmol420.redis_springboot.exceptions.DuplicateResourceException;
import com.anmol420.redis_springboot.exceptions.ResourceNotFoundException;
import com.anmol420.redis_springboot.repository.BookRepository;
import com.anmol420.redis_springboot.services.BookService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public List<Book> getBooks() {
        try {
            return bookRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    @CachePut(value = "books", key = "#book.id")
    @Override
    public Book addBook(Book book) {
        boolean bookFound = bookRepository.existsByNameIgnoreCase(book.getName());
        if (bookFound) {
            throw new DuplicateResourceException("Book with Name: " + book.getName() + " already exists.");
        }
        try {
            book.setCreatedAt(LocalDateTime.now());
            book.setUpdatedAt(LocalDateTime.now());
            return bookRepository.save(book);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Cacheable(value = "books", key = "#id")
    @Override
    public Optional<Book> getBookById(UUID id) {
        try {
            return bookRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    @CachePut(value = "books", key = "#id")
    @Override
    public Book updateBook(UUID id, Book book) {
        if (null == id) {
            throw new IllegalArgumentException("ID is null.");
        }
        Book bookFound = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book does not exist."));
        try {
            bookFound.setName(book.getName());
            bookFound.setDescription(book.getDescription());
            bookFound.setAuthorName(book.getAuthorName());
            bookFound.setUpdatedAt(LocalDateTime.now());
            return bookRepository.save(bookFound);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    @CacheEvict(value = "books", key = "#id")
    @Override
    public void deleteBook(UUID id) {
        if (null == id) {
            throw new IllegalArgumentException("ID is null.");
        }
        bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book does not exist."));
        try {
            bookRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

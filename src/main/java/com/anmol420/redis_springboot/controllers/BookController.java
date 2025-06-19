package com.anmol420.redis_springboot.controllers;

import com.anmol420.redis_springboot.domain.dtos.BookDTO;
import com.anmol420.redis_springboot.mappers.BookMapper;
import com.anmol420.redis_springboot.services.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    @GetMapping(path = "/getBooks")
    public ResponseEntity<List<BookDTO>> getBooks() {
        List<BookDTO> books = bookService.getBooks()
                .stream().map(bookMapper::toDTO).toList();
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }

    @PostMapping(path = "/addBook")
    public ResponseEntity<?> addBook(
            @Valid @RequestBody BookDTO bookDTO
    ) {
        BookDTO createdBook = bookMapper.toDTO(bookService.addBook(bookMapper.fromDTO(bookDTO)));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    @GetMapping(path = "/getBookById/{id}")
    public ResponseEntity<Optional<BookDTO>> getBookById(
            @PathVariable UUID id
    ) {
        Optional<BookDTO> book = bookService.getBookById(id).map(bookMapper::toDTO);
        return ResponseEntity.status(HttpStatus.OK).body(book);
    }

    @PatchMapping(path = "/updateBook/{id}")
    public ResponseEntity<BookDTO> updateBook(
            @PathVariable UUID id,
            @Valid @RequestBody BookDTO bookDTO
    ) {
        BookDTO book = bookMapper.toDTO(bookService.updateBook(id, bookMapper.fromDTO(bookDTO)));
        return ResponseEntity.status(HttpStatus.OK).body(book);
    }

    @DeleteMapping(path = "/deleteBook/{id}")
    public ResponseEntity<?> deleteBook(
            @PathVariable UUID id
    ) {
        bookService.deleteBook(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted!");
    }

}

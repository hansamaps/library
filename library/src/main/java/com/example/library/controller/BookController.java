package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    // Add a new book
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book saved = bookService.addBook(book);
        return ResponseEntity.status(201).body(saved);
    }

    // Get all books
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    // Get book by ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable String id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    // Update book by ID
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable String id, @RequestBody Book book) {
        return ResponseEntity.ok(bookService.updateBook(id, book));
    }

    // Delete book by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable String id) {
        bookService.deleteBookById(id);
        return ResponseEntity.noContent().build();
    }

    // Find books by publication year
    @GetMapping("/year/{year}")
    public ResponseEntity<List<Book>> getBooksByYear(@PathVariable Integer year) {
        return ResponseEntity.ok(bookService.findBooksByPublicationYear(year));
    }

    // Get genre of a book by ID
    @GetMapping("/{id}/genre")
    public ResponseEntity<String> getGenre(@PathVariable String id) {
        return ResponseEntity.ok(bookService.getGenreByBookId(id));
    }

    // Delete all books published in a specific year
    @DeleteMapping("/year/{year}")
    public ResponseEntity<Void> deleteBooksByYear(@PathVariable Integer year) {
        bookService.deleteBooksByPublicationYear(year);
        return ResponseEntity.noContent().build();
    }
}


package com.example.library.service.impl;

import com.example.library.exception.BookNotFoundException;
import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import com.example.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public Book addBook(Book book) {
        if (book.getCopiesAvailable() == null) {
            book.setCopiesAvailable(1);
        }
        return bookRepository.save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(String id) {
        return bookRepository.findById(id)
            .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));
    }

    @Override
    public Book updateBook(String id, Book updated) {
        Book existing = getBookById(id);
        // update fields (null checks optional)
        existing.setTitle(updated.getTitle() != null ? updated.getTitle() : existing.getTitle());
        existing.setAuthor(updated.getAuthor() != null ? updated.getAuthor() : existing.getAuthor());
        existing.setGenre(updated.getGenre() != null ? updated.getGenre() : existing.getGenre());
        existing.setPublicationYear(updated.getPublicationYear() != null ? updated.getPublicationYear() : existing.getPublicationYear());
        existing.setIsbn(updated.getIsbn() != null ? updated.getIsbn() : existing.getIsbn());
        existing.setCopiesAvailable(updated.getCopiesAvailable() != null ? updated.getCopiesAvailable() : existing.getCopiesAvailable());
        return bookRepository.save(existing);
    }

    @Override
    public void deleteBookById(String id) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> findBooksByPublicationYear(Integer year) {
        return bookRepository.findByPublicationYear(year);
    }

    @Override
    public String getGenreByBookId(String id) {
        Book book = getBookById(id);
        return book.getGenre();
    }

    @Override
    public void deleteBooksByPublicationYear(Integer year) {
        bookRepository.deleteByPublicationYear(year);
    }
}


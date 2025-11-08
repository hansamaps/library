package com.example.library.repository;

import com.example.library.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {
    // Derived query methods
    List<Book> findByPublicationYear(Integer year);

    // Delete all books by publication year
    void deleteByPublicationYear(Integer year);

    // Optionally find by ISBN
    Book findByIsbn(String isbn);
}

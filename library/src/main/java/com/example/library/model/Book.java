package com.example.library.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    @Id
    private String id;

    private String title;
    private String author;
    private String genre;
    private Integer publicationYear;
    private String isbn;
    private Integer copiesAvailable;
}





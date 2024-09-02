package com.project.library.book;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class BookDto {
    private Long id;
    private String title;
    private String author;
    private String imageURL;
    private String description;
    private String isbn;
    private String publisher;
    private String pubdate;

    @Builder
    public BookDto(String title, String author, String imageURL, String description, String isbn, String publisher, String pubdate) {
        this.title = title;
        this.author = author;
        this.imageURL = imageURL;
        this.description = description;
        this.isbn = isbn;
        this.publisher = publisher;
        this.pubdate = pubdate;
    }
}

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

    @Builder
    public BookDto(String title, String author, String imageURL) {
        this.title = title;
        this.author = author;
        this.imageURL = imageURL;
    }
}

package com.example.book_test.dto;

import com.example.book_test.domain.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class KakaoBookResponse {

    private List<Document> documents;

    @Getter
    @Setter
    public static class Document {
        private String title;
        private List<String> authors;
        private String isbn;
        private String publisher;
        private String datetime;
        private int price;
        private int sale_price;
        private String thumbnail;
        private String status;
        private String contents;
    }
    public List<Book> toEntities() {
        return documents.stream().map(doc -> Book.builder()
                .title(doc.getTitle())
                .author(doc.getAuthors().isEmpty() ? null : doc.getAuthors().get(0)) // 첫 번째 저자
                .isbn(doc.getIsbn())
                .publisher(doc.getPublisher())
                .publishedAt(OffsetDateTime.parse(doc.getDatetime()))
                .price(doc.getPrice())
                .salePrice(doc.getSale_price())
                .thumbnailUrl(doc.getThumbnail())
                .status(doc.getStatus())
                .description(doc.getContents())
                .createdAt(LocalDateTime.now())
                .build()
        ).collect(Collectors.toList());
    }

    }




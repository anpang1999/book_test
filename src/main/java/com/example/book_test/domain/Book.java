package com.example.book_test.domain;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String title;

    @Column(columnDefinition = "TEXT")
    private String author;

    @Column(columnDefinition = "TEXT")
    private String isbn;

    @Column(columnDefinition = "TEXT")
    private String publisher;

    private OffsetDateTime publishedAt;

    private Integer price;

    private Integer salePrice;

    @Column(columnDefinition = "TEXT")
    private String thumbnailUrl;

    @Column(columnDefinition = "TEXT")
    private String status;

    @Column(columnDefinition = "TEXT")
    private String description;

    private LocalDateTime createdAt;


}

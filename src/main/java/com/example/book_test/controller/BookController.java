package com.example.book_test.controller;

import com.example.book_test.domain.Book;
import com.example.book_test.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchAndSave(@RequestParam String query) {
        List<Book> savedBooks = bookService.searchAndSaveBooks(query);
        return ResponseEntity.ok(savedBooks);
    }
}

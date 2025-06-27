package com.example.book_test.repository;

import com.example.book_test.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByIsbn(String isbn); // 중복 방지에 유용
}

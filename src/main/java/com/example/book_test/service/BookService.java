package com.example.book_test.service;

import com.example.book_test.domain.Book;
import com.example.book_test.dto.KakaoBookResponse;
import com.example.book_test.dto.KakaoBookResponse.Document;
import com.example.book_test.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    @Value("${kakao.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;
    private final BookRepository bookRepository;

    /**
     * 카카오 도서 검색 API 호출 후 결과를 DB에 저장하고 저장된 Book 목록을 반환합니다.
     *
     * @param query 검색어
     * @return 저장된 Book 엔티티 목록
     */
    @Transactional
    public List<Book> searchAndSaveBooks(String query) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK " + apiKey);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        String url = UriComponentsBuilder.fromHttpUrl("https://dapi.kakao.com/v3/search/book")
                .queryParam("query", query)
                .build()
                .toUriString();

        ResponseEntity<KakaoBookResponse> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                KakaoBookResponse.class
        );

        List<Book> books = response.getBody().toEntities();
        return bookRepository.saveAll(books);
    }

}
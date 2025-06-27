package com.example.book_test.service;

import com.example.book_test.dto.KakaoBookResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class KakaoBookClient {

    private final RestTemplate restTemplate = new RestTemplate();

    private static final String KAKAO_API_URL = "https://dapi.kakao.com/v3/search/book";

    @Value("${kakao.api.key}")
    private String kakaoApiKey;

    public KakaoBookResponse search(String query) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", kakaoApiKey);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        UriComponents uri = UriComponentsBuilder.fromHttpUrl("https://dapi.kakao.com/v3/search/book")
                .queryParam("query", query)
                .build();

        ResponseEntity<KakaoBookResponse> response = restTemplate.exchange(
                uri.toUri(),
                HttpMethod.GET,
                entity,
                KakaoBookResponse.class
        );

        return response.getBody();
    }
}

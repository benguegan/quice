package com.portfolio.quice;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
class IsbnRestController {

  private final RestTemplate restTemplate;

  IsbnRestController(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @GetMapping("/books/{isbn}")
  String lookupBookByIsbn(@PathVariable("isbn") String isbn) {
    ResponseEntity<String> exchange = this.restTemplate.exchange(
        "https://www.googleapis.com/books/v1/volumes?q=isbn:" + isbn, HttpMethod.GET,
        null,
        String.class);

    return exchange.getBody();
  }

}
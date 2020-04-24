package com.wolox.trainnerInMaven.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wolox.trainnerInMaven.exceptions.BookNotFoundException;
import com.wolox.trainnerInMaven.models.DTOs.BookDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class OpenLibraryService {
    private final static String URL = "https://openlibrary.org/api/books?format=json&jscmd=data&bibkeys=ISBN:";
    private final static String DEFAULT_SUBTITLE = "DEFAULT_SUBTITLE";

    public BookDTO bookInfo(String isbn) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(URL + isbn, String.class);
        if (response.getStatusCode() == HttpStatus.NOT_FOUND)
            throw new BookNotFoundException("Mismatch ISBN book", null);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = null;
        try {
            root = mapper.readTree(response.getBody());
        } catch (IOException e) {
            throw new BookNotFoundException("error to mapper book", e);
        }
        BookDTO bookDTO = mapper.convertValue(root.path("ISBN:" +isbn), BookDTO.class);
        bookDTO.setImage(mapper.convertValue(root.get("ISBN:" +isbn).get("cover").get("medium"), String.class));
        bookDTO.setIsbn(isbn);
        if (bookDTO.getSubtitle() == null)
            bookDTO.setSubtitle(DEFAULT_SUBTITLE);
        return bookDTO;
    }
}

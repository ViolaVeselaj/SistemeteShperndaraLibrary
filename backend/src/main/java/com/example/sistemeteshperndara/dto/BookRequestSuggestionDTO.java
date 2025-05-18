package com.example.sistemeteshperndara.dto;

import lombok.Data;

@Data
public class BookRequestSuggestionDTO {
    private String title;
    private String author;
    private String genre;
    private Long userId;
}

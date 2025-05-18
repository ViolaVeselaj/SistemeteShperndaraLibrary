package com.example.sistemeteshperndara.dto;

import lombok.Data;

@Data
public class QuoteDTO {
    private String content;
    private Integer pageNumber;
    private Long userId;
    private Long bookId;
    private Long tenantId;
}

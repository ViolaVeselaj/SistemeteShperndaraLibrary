package com.example.sistemeteshperndara.dto;

import lombok.Data;
import java.time.Instant;

@Data
public class LoanRequestDTO {
    private Long bookId;
    private Instant borrowDate;
    private Instant returnDate;
}

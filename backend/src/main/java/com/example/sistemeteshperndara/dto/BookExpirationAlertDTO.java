package com.example.sistemeteshperndara.dto;

import lombok.Data;

@Data
public class BookExpirationAlertDTO {
    private Long userId;
    private Long loanId;
    private int daysRemaining;
}

package com.example.sistemeteshperndara.dto;

import lombok.Data;

@Data
public class AbuseReportDTO {
    private Long reviewId;
    private Long reportingUserId;
    private String reason;
    private Long tenantId;
}

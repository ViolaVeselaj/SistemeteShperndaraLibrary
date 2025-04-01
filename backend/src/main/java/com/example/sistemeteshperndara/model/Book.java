package com.example.sistemeteshperndara.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "published_year", nullable = false)
    private Integer publishedYear;

    @Column(name = "available_copies", nullable = false)
    private Integer availableCopies;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private Instant createdAt;

    @ColumnDefault("1")
    @Column(name = "tenant_id", nullable = false)
    private Long tenantId;

    @Column(name = "author")
    private String author1;

}
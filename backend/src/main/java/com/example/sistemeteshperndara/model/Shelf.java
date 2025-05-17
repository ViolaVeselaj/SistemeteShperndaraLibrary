package com.example.sistemeteshperndara.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "shelves")
public class Shelf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // p.sh. A1, B3, etj.

    @Column
    private String location; // opsionale për përshkrim më të gjatë

    @Column(nullable = false)
    private Long tenantId;
}

package com.example.sistemeteshperndara.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.example.sistemeteshperndara.model.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface BookRepository extends JpaRepository<Book, Long> {

}


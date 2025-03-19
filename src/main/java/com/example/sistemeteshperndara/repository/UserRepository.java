package com.example.sistemeteshperndara.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.sistemeteshperndara.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
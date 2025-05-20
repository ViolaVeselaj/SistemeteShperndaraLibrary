package com.example.sistemeteshperndara.repository;
import com.example.sistemeteshperndara.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<User> findByEmailAndTenantId(String email, Long tenantId);


    List<User> findAllByTenantId(Long tenantId);

}
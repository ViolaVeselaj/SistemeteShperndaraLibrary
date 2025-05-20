package com.example.sistemeteshperndara.repository;

import com.example.sistemeteshperndara.model.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantRepository extends JpaRepository<Tenant, Long> {
}

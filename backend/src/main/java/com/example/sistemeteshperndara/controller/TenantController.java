package com.example.sistemeteshperndara.controller;

import com.example.sistemeteshperndara.model.Tenant;
import com.example.sistemeteshperndara.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tenants")
@CrossOrigin(origins = "http://localhost:3000")
public class TenantController {

    @Autowired
    private TenantRepository tenantRepository;

    @GetMapping
    public ResponseEntity<List<Tenant>> getAllTenants() {
        return ResponseEntity.ok(tenantRepository.findAll());
    }
}

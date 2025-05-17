package com.example.sistemeteshperndara.service;

import com.example.sistemeteshperndara.model.Publisher;
import com.example.sistemeteshperndara.repository.PublisherRepository;
import com.example.sistemeteshperndara.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private CurrentUser currentUser;

    public List<Publisher> getAll() {
        Long tenantId = currentUser.getTenantIdFromToken();
        return publisherRepository.findByTenantId(tenantId);
    }

    public void save(Publisher publisher) {
        Long tenantId = currentUser.getTenantIdFromToken();
        publisher.setTenantId(tenantId);
        publisherRepository.save(publisher);
    }

    public Publisher getById(Long id) {
        return publisherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publisher not found"));
    }
}

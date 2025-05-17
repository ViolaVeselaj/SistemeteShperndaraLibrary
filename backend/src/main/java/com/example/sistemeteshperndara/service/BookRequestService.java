package com.example.sistemeteshperndara.service;

import com.example.sistemeteshperndara.model.BookRequest;
import com.example.sistemeteshperndara.repository.BookRequestRepository;
import com.example.sistemeteshperndara.security.CurrentUser;
import com.example.sistemeteshperndara.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookRequestService {

    @Autowired
    private BookRequestRepository bookRequestRepository;

    @Autowired
    private CurrentUser currentUser;

    @Autowired
    private UserRepository userRepository;

    public void createRequest(BookRequest bookRequest) {
        Long userId = currentUser.getUserIdFromToken();
        Long tenantId = currentUser.getTenantIdFromToken();
        bookRequest.setTenantId(tenantId);
        bookRequest.setUser(userRepository.findById(userId).orElseThrow());
        bookRequestRepository.save(bookRequest);
    }

    public List<BookRequest> getAllRequestsForTenant() {
        Long tenantId = currentUser.getTenantIdFromToken();
        return bookRequestRepository.findByTenantId(tenantId);
    }
}


package com.example.sistemeteshperndara.service;

import com.example.sistemeteshperndara.model.Book;
import com.example.sistemeteshperndara.model.ReadingHistory;
import com.example.sistemeteshperndara.model.User;
import com.example.sistemeteshperndara.repository.BookRepository;
import com.example.sistemeteshperndara.repository.ReadingHistoryRepository;
import com.example.sistemeteshperndara.repository.UserRepository;
import com.example.sistemeteshperndara.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class ReadingHistoryService {

    @Autowired
    private ReadingHistoryRepository historyRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CurrentUser currentUser;

    public void addHistory(Long bookId, Instant startDate, Instant endDate, String status) {
        Long userId = currentUser.getUserIdFromToken();
        Long tenantId = currentUser.getTenantIdFromToken();

        Book book = bookRepository.findById(bookId).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();

        ReadingHistory history = new ReadingHistory();
        history.setBook(book);
        history.setUser(user);
        history.setStartDate(startDate);
        history.setEndDate(endDate);
        history.setStatus(status);
        history.setTenantId(tenantId);

        historyRepository.save(history);
    }

    public List<ReadingHistory> getUserHistory() {
        Long userId = currentUser.getUserIdFromToken();
        Long tenantId = currentUser.getTenantIdFromToken();
        return historyRepository.findByUserIdAndTenantId(userId, tenantId);
    }
}

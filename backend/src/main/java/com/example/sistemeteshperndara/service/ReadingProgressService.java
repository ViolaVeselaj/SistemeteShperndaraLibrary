package com.example.sistemeteshperndara.service;

import com.example.sistemeteshperndara.model.Book;
import com.example.sistemeteshperndara.model.ReadingProgress;
import com.example.sistemeteshperndara.model.User;
import com.example.sistemeteshperndara.repository.BookRepository;
import com.example.sistemeteshperndara.repository.ReadingProgressRepository;
import com.example.sistemeteshperndara.repository.UserRepository;
import com.example.sistemeteshperndara.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadingProgressService {

    @Autowired
    private ReadingProgressRepository readingProgressRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CurrentUser currentUser;

    public void saveProgress(Long bookId, Integer currentPage, Integer totalPages) {
        Long userId = currentUser.getUserIdFromToken();
        Long tenantId = currentUser.getTenantIdFromToken();

        User user = userRepository.findById(userId).orElseThrow();
        Book book = bookRepository.findById(bookId).orElseThrow();

        ReadingProgress progress = new ReadingProgress();
        progress.setUser(user);
        progress.setBook(book);
        progress.setCurrentPage(currentPage);
        progress.setTotalPages(totalPages);
        progress.setTenantId(tenantId);

        readingProgressRepository.save(progress);
    }

    public List<ReadingProgress> getProgressForUser() {
        Long userId = currentUser.getUserIdFromToken();
        Long tenantId = currentUser.getTenantIdFromToken();
        return readingProgressRepository.findByUserIdAndTenantId(userId, tenantId);
    }
}

package com.example.sistemeteshperndara.service;

import com.example.sistemeteshperndara.dto.BookExpirationAlertDTO;
import com.example.sistemeteshperndara.model.BookExpirationAlert;
import com.example.sistemeteshperndara.model.Loan;
import com.example.sistemeteshperndara.model.User;
import com.example.sistemeteshperndara.repository.BookExpirationAlertRepository;
import com.example.sistemeteshperndara.repository.LoanRepository;
import com.example.sistemeteshperndara.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookExpirationAlertService {

    @Autowired
    private BookExpirationAlertRepository alertRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoanRepository loanRepository;

    public BookExpirationAlert createAlert(BookExpirationAlertDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Loan loan = loanRepository.findById(dto.getLoanId())
                .orElseThrow(() -> new IllegalArgumentException("Loan not found"));

        BookExpirationAlert alert = new BookExpirationAlert();
        alert.setUser(user);
        alert.setLoan(loan);
        alert.setDaysRemaining(dto.getDaysRemaining());
        alert.setAlertSentAt(java.time.Instant.now());

        return alertRepository.save(alert);
    }
}

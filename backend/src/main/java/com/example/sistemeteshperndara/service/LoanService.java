package com.example.sistemeteshperndara.service;

import com.example.sistemeteshperndara.model.Book;
import com.example.sistemeteshperndara.model.Loan;
import com.example.sistemeteshperndara.model.User;
import com.example.sistemeteshperndara.repository.LoanRepository;
import com.example.sistemeteshperndara.security.CurrentUser;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.sistemeteshperndara.repository.FineRepository;
import java.math.BigDecimal;
import com.example.sistemeteshperndara.model.Fine;
import com.example.sistemeteshperndara.repository.FineRepository;
import java.time.temporal.ChronoUnit;





import java.time.Instant;
import java.util.List;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private CurrentUser currentUser;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private FineRepository fineRepository;


    public List<Loan> getAllLoans() {
        Long tenantId = currentUser.getTenantIdFromToken();
        return loanRepository.findByTenantId(tenantId);
    }

    public void saveLoan(Loan loan) {
        Long tenantId = currentUser.getTenantIdFromToken();
        loan.setTenantId(tenantId);
        loan.setStatus("PENDING");
        loanRepository.save(loan);
    }

    public List<Loan> getPendingLoans() {
        Long tenantId = currentUser.getTenantIdFromToken();
        return loanRepository.findByTenantIdAndStatus(tenantId, "PENDING");
    }

    public void updateStatus(Long loanId, String newStatus) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));
        loan.setStatus(newStatus);
        loanRepository.save(loan);

        if ("RETURNED".equalsIgnoreCase(newStatus)) {
            checkAndApplyFines();
        }
    }

    public void createLoanRequest(Long bookId, Instant borrowDate, Instant returnDate) {
        Long tenantId = currentUser.getTenantIdFromToken();
        Long userId = currentUser.getUserIdFromToken();

        if (bookId == null) {
            throw new IllegalArgumentException("Book ID must not be null");
        }
        if (userId == null) {
            throw new IllegalStateException("User ID could not be extracted from token");
        }

        Loan loan = new Loan();
        loan.setTenantId(tenantId);
        loan.setStatus("PENDING");
        loan.setBorrowDate(borrowDate);
        loan.setReturnDate(returnDate);

        // përdor EntityManager për referenca të menaxhuara
        User user = entityManager.getReference(User.class, userId);
        Book book = entityManager.getReference(Book.class, bookId);

        loan.setUser(user);
        loan.setBook(book);

        loanRepository.save(loan);
    }

    public void checkAndApplyFines() {
        Long tenantId = currentUser.getTenantIdFromToken();
        List<Loan> loans = loanRepository.findByTenantId(tenantId);

        for (Loan loan : loans) {
            if (loan.getReturnDate() != null && Instant.now().isAfter(loan.getReturnDate())) {
                // Kontrollo nëse ka gjobë ekzistuese për këtë huazim
                boolean alreadyFined = fineRepository
                        .findAll()
                        .stream()
                        .anyMatch(f -> f.getLoan().getId().equals(loan.getId()));

                if (!alreadyFined) {
                    long overdueDays = ChronoUnit.DAYS.between(loan.getReturnDate(), Instant.now());
                    BigDecimal amount = BigDecimal.valueOf(overdueDays * 0.50); // 0.50€/ditë

                    Fine fine = new Fine();
                    fine.setTenantId(tenantId);
                    fine.setLoan(loan);
                    fine.setUser(loan.getUser());
                    fine.setAmount(amount);
                    fine.setIssuedDate(Instant.now());
                    fine.setPaid(false);

                    fineRepository.save(fine);
                }
            }
        }
    }

}

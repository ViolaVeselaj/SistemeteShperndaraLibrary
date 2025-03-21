package com.example.sistemeteshperndara.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.sistemeteshperndara.model.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}

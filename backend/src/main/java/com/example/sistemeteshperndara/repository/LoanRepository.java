package com.example.sistemeteshperndara.repository;
import com.example.sistemeteshperndara.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}

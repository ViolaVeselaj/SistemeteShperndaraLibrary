package com.example.sistemeteshperndara.repository;
import com.example.sistemeteshperndara.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByTenantId(Long tenantId);
}

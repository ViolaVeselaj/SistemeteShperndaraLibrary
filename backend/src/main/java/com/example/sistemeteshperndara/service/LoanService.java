package com.example.sistemeteshperndara.service;

import com.example.sistemeteshperndara.model.Loan;
import com.example.sistemeteshperndara.repository.LoanRepository;
import com.example.sistemeteshperndara.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private CurrentUser currentUser;

    public List<Loan> getAllLoans() {
        Long tenantId = currentUser.getTenantIdFromToken();
        return loanRepository.findByTenantId(tenantId);
    }

    public void saveLoan(Loan loan) {
        Long tenantId = currentUser.getTenantIdFromToken();
        loan.setTenantId(tenantId);
        loanRepository.save(loan);
    }
}

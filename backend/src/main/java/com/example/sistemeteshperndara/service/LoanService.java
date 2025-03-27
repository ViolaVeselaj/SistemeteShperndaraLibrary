package com.example.sistemeteshperndara.service;

import com.example.sistemeteshperndara.model.Loan;
import com.example.sistemeteshperndara.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public void saveLoan(Loan loan) {
        loanRepository.save(loan);
    }
}
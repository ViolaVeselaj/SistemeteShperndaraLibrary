package com.example.sistemeteshperndara.controller;

import com.example.sistemeteshperndara.model.Loan;
import com.example.sistemeteshperndara.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @GetMapping
    public List<Loan> getAllLoans() {
        return loanService.getAllLoans();
    }

    @PostMapping("/add")
    public String addLoan(@RequestBody Loan loan) {
        loanService.saveLoan(loan);
        return "Huazimi u shtua me sukses!";
    }
}

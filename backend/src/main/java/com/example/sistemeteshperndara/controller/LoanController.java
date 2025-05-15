package com.example.sistemeteshperndara.controller;

import com.example.sistemeteshperndara.model.Loan;
import com.example.sistemeteshperndara.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.sistemeteshperndara.dto.LoanRequestDTO;
import org.springframework.http.ResponseEntity;
import com.example.sistemeteshperndara.repository.FineRepository;

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
    public String requestLoan(@RequestBody LoanRequestDTO request) {
        loanService.createLoanRequest(
                request.getBookId(),
                request.getBorrowDate(),
                request.getReturnDate());
        return "Kërkesa për huazim u dërgua me sukses!";
    }
    @GetMapping("/pending")
    public List<Loan> getPendingLoans() {
        return loanService.getPendingLoans();
    }

    @PutMapping("/{id}/approve")
    public String approveLoan(@PathVariable Long id) {
        loanService.updateStatus(id, "APPROVED");
        return "Loan approved";
    }

    @PutMapping("/{id}/reject")
    public String rejectLoan(@PathVariable Long id) {
        loanService.updateStatus(id, "REJECTED");
        return "Loan rejected";
    }

    @PutMapping("/check-fines")
    public ResponseEntity<String> checkFines() {
        loanService.checkAndApplyFines();
        return ResponseEntity.ok("Fines checked and applied where necessary.");
    }


}

package com.example.sistemeteshperndara.service;

import com.example.sistemeteshperndara.dto.QuoteDTO;
import com.example.sistemeteshperndara.model.Book;
import com.example.sistemeteshperndara.model.Quote;
import com.example.sistemeteshperndara.model.User;
import com.example.sistemeteshperndara.repository.BookRepository;
import com.example.sistemeteshperndara.repository.QuoteRepository;
import com.example.sistemeteshperndara.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuoteService {

    @Autowired
    private QuoteRepository quoteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    public Quote createQuote(QuoteDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Book book = bookRepository.findById(dto.getBookId())
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        Quote quote = new Quote();
        quote.setContent(dto.getContent());
        quote.setPageNumber(dto.getPageNumber());
        quote.setTenantId(dto.getTenantId());
        quote.setUser(user);
        quote.setBook(book);
        quote.setCreatedAt(java.time.Instant.now());

        return quoteRepository.save(quote);
    }
}

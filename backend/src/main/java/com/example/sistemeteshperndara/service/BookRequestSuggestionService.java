package com.example.sistemeteshperndara.service;

import com.example.sistemeteshperndara.dto.BookRequestSuggestionDTO;
import com.example.sistemeteshperndara.model.BookRequestSuggestion;
import com.example.sistemeteshperndara.model.User;
import com.example.sistemeteshperndara.repository.BookRequestSuggestionRepository;
import com.example.sistemeteshperndara.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookRequestSuggestionService {

    @Autowired
    private BookRequestSuggestionRepository suggestionRepository;

    @Autowired
    private UserRepository userRepository;

    public BookRequestSuggestion createSuggestion(BookRequestSuggestionDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        BookRequestSuggestion suggestion = new BookRequestSuggestion();
        suggestion.setTitle(dto.getTitle());
        suggestion.setAuthor(dto.getAuthor());
        suggestion.setGenre(dto.getGenre());
        suggestion.setUser(user);
        suggestion.setStatus(BookRequestSuggestion.Status.PENDING);
        suggestion.setSuggestedAt(java.time.Instant.now());

        return suggestionRepository.save(suggestion);
    }
}

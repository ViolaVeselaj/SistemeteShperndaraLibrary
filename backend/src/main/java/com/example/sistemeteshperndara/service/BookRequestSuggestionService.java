package com.example.sistemeteshperndara.service;

import com.example.sistemeteshperndara.dto.BookRequestSuggestionDTO;
import com.example.sistemeteshperndara.model.BookRequestSuggestion;
import com.example.sistemeteshperndara.model.User;
import com.example.sistemeteshperndara.repository.BookRequestSuggestionRepository;
import com.example.sistemeteshperndara.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Instant;


@Service
public class BookRequestSuggestionService {

    @Autowired
    private BookRequestSuggestionRepository suggestionRepository;

    @Autowired
    private UserService userService;

    public BookRequestSuggestion createSuggestion(BookRequestSuggestionDTO dto) {
        User user = userService.getCurrentUser();

        BookRequestSuggestion suggestion = new BookRequestSuggestion();
        suggestion.setTitle(dto.getTitle());
        suggestion.setAuthor(dto.getAuthor());
        suggestion.setGenre(dto.getGenre());
        suggestion.setUser(user);
        suggestion.setStatus(BookRequestSuggestion.Status.PENDING);
        suggestion.setSuggestedAt(Instant.now());

        return suggestionRepository.save(suggestion);
    }
}


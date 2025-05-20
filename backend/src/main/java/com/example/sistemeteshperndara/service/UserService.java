package com.example.sistemeteshperndara.service;

import com.example.sistemeteshperndara.model.User;
import com.example.sistemeteshperndara.repository.UserRepository;
import com.example.sistemeteshperndara.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CurrentUser currentUser;

    @Cacheable("users")
    public List<User> getAllUsers() {
        System.out.println("Leximi i përdoruesve nga databaza");
        return userRepository.findAll();
    }

    @CacheEvict(value = "users", allEntries = true)
    public void saveUser(User user) {
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<User> getUsersByTenantId(Long tenantId) {
        return userRepository.findAllByTenantId(tenantId);
    }

    public boolean checkPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public User getCurrentUser() {
        Long userId = currentUser.getUserIdFromToken();
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
    }
}

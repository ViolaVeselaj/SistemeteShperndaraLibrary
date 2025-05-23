package com.example.sistemeteshperndara.controller;

import com.example.sistemeteshperndara.dto.AuthRequest;
import com.example.sistemeteshperndara.dto.AuthResponse;
import com.example.sistemeteshperndara.model.Role;
import com.example.sistemeteshperndara.model.User;
import com.example.sistemeteshperndara.repository.UserRepository;
import com.example.sistemeteshperndara.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest loginRequest) {
        try {
            // Gjej user-in me email + tenantId
            Optional<User> optionalUser = userRepository.findByEmailAndTenantId(
                    loginRequest.getEmail(),
                    loginRequest.getTenantId()
            );

            if (optionalUser.isEmpty()) {
                return ResponseEntity.status(401).body("User not found for the specified tenant");
            }

            User user = optionalUser.get();

            // Verifiko password-in
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            String token = jwtService.generateToken(userDetails, user.getTenantId(), user.getId());
            if (token == null || token.isBlank()) {
                return ResponseEntity.status(500).body("Failed to generate JWT token");
            }

            String roleName = user.getRoles().stream()
                    .findFirst()
                    .map(Role::getName)
                    .orElse("UNKNOWN");

            AuthResponse response = new AuthResponse();
            response.setToken("Bearer " + token);
            response.setEmail(user.getEmail());
            response.setName(user.getName());
            response.setRole(roleName);

            return ResponseEntity.ok(response);

        } catch (Exception ex) {
            return ResponseEntity.status(403).body("Login failed: " + ex.getMessage());
        }
    }

}

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
            // ✅ Authentikimi i përdoruesit
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );

            // ✅ Gjej user-in në databazë
            Optional<User> optionalUser = userRepository.findByEmail(loginRequest.getEmail());
            if (optionalUser.isEmpty()) {
                return ResponseEntity.status(401).body("User not found");
            }

            User user = optionalUser.get();
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // ✅ Gjenero JWT Token me informacionet e nevojshme
            String token = jwtService.generateToken(userDetails, user.getTenantId(), user.getId());

            if (token == null || token.isBlank()) {
                return ResponseEntity.status(500).body("Failed to generate JWT token");
            }

            // ✅ Merr rolin e parë të user-it (nëse ekziston)
            String roleName = user.getRoles().stream()
                    .findFirst()
                    .map(Role::getName)
                    .orElse("UNKNOWN");

            // ✅ Krijo objektin AuthResponse për frontend-in
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

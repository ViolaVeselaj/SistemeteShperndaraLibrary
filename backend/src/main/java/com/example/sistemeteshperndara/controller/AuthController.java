package com.example.sistemeteshperndara.controller;

import com.example.sistemeteshperndara.dto.AuthRequest;
import com.example.sistemeteshperndara.dto.AuthResponse;
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
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        Optional<User> optionalUser = userRepository.findByEmail(loginRequest.getEmail());
        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(401).body("User not found");
        }

        User user = optionalUser.get();  // Merr të dhënat e user-it nga DB
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // ✅ Tani përfshijmë userId në token
        String token = jwtService.generateToken(userDetails, user.getTenantId(), user.getId());

        AuthResponse response = new AuthResponse();
        response.setToken(token);
        response.setEmail(user.getEmail());
        response.setName(user.getName());
        response.setRole(user.getRole().name());

        return ResponseEntity.ok(response);
    }
}

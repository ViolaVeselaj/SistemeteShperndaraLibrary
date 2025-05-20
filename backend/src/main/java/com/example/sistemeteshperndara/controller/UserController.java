package com.example.sistemeteshperndara.controller;

import com.example.sistemeteshperndara.dto.AuthRequest;
import com.example.sistemeteshperndara.dto.AuthResponse;
import com.example.sistemeteshperndara.model.Role;
import com.example.sistemeteshperndara.model.User;
import com.example.sistemeteshperndara.repository.RoleRepository;
import com.example.sistemeteshperndara.repository.UserRepository;
import com.example.sistemeteshperndara.security.JwtService;
import com.example.sistemeteshperndara.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired private UserService userService;
    @Autowired private RoleRepository roleRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private JwtService jwtService;
    @Autowired private AuthenticationManager authenticationManager;

    @PreAuthorize("hasAuthority('GET:/users')")
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody AuthRequest loginRequest) {
        try {
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

            User user = optionalUser.get();
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            String token = jwtService.generateToken(userDetails, user.getTenantId(), user.getId());
            String roleName = user.getRoles().stream().findFirst().map(Role::getName).orElse("UNKNOWN");

            AuthResponse response = new AuthResponse();
            response.setToken("Bearer " + token);
            response.setEmail(user.getEmail());
            response.setName(user.getName());
            response.setRole(roleName);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(403).body("Login failed: " + e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('GET:/users/by-tenant')")
    @GetMapping("/by-tenant")
    public ResponseEntity<?> getUsersByTenant(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(401).body("Unauthorized");
        }

        String email = authentication.getName();
        User currentUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<User> users = userService.getUsersByTenantId(currentUser.getTenantId());
        return ResponseEntity.ok(users);
    }
    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        if (user.getName() == null || user.getEmail() == null || user.getPassword() == null || user.getTenantId() == null) {
            return ResponseEntity.badRequest().body("All fields including tenantId are required.");
        }

        Role defaultRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("Default role not found"));

        user.setRoles(Set.of(defaultRole));
        userService.saveUser(user);

        return ResponseEntity.ok("User registered successfully!");
    }
}


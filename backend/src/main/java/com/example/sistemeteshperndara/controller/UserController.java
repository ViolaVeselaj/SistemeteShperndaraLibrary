package com.example.sistemeteshperndara.controller;

import com.example.sistemeteshperndara.dto.AuthRequest;
import com.example.sistemeteshperndara.dto.AuthResponse;
import com.example.sistemeteshperndara.model.Role;
import com.example.sistemeteshperndara.model.User;
import com.example.sistemeteshperndara.repository.RoleRepository;
import com.example.sistemeteshperndara.security.JwtService;
import com.example.sistemeteshperndara.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody AuthRequest loginRequest) {
        User user = userService.findByEmail(loginRequest.getEmail());

        boolean passwordMatches = userService.checkPassword(loginRequest.getPassword(), user.getPassword());
        if (!passwordMatches) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }

        List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
                .flatMap(role -> role.getPermissions().stream())
                .map(p -> new SimpleGrantedAuthority(p.getHttpMethod() + ":" + p.getUrlPattern()))
                .toList();

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                authorities
        );

        String token = jwtService.generateToken(userDetails, user.getTenantId(), user.getId());

        AuthResponse response = new AuthResponse();
        response.setName(user.getName());
        response.setEmail(user.getEmail());


        String roleName = user.getRoles().stream().findFirst().map(Role::getName).orElse("UNKNOWN");
        response.setRole(roleName);

        response.setToken("Bearer " + token);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        if (user.getName() == null || user.getEmail() == null || user.getPassword() == null) {
            return ResponseEntity.badRequest().body("All fields are required.");
        }

        // P.sh. vendos "USER" si rol default
        Role role = roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("Default role not found"));

        user.setRoles(Set.of(role));
        user.setTenantId(1L);
        userService.saveUser(user);

        return ResponseEntity.ok("User registered successfully!");
    }
}

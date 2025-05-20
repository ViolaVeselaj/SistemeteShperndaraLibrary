package com.example.sistemeteshperndara.controller;

import com.example.sistemeteshperndara.dto.AuthResponse;
import com.example.sistemeteshperndara.dto.AuthRequest;
import com.example.sistemeteshperndara.model.Role;
import com.example.sistemeteshperndara.model.User;
import com.example.sistemeteshperndara.repository.RoleRepository;
import com.example.sistemeteshperndara.repository.UserRepository;
import com.example.sistemeteshperndara.security.JwtService;
import com.example.sistemeteshperndara.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setId(1L);
        testUser.setName("Test User");
        testUser.setEmail("test@example.com");
        testUser.setPassword("hashedPass");
        testUser.setTenantId(1L);
        testUser.setRoles(Set.of(new Role())); // assuming Role() has setters
        testUser.getRoles().iterator().next().setName("USER");
    }

    @Test
    void getAllUsers() {
        when(userService.getAllUsers()).thenReturn(List.of(testUser));

        List<User> response = userController.getAllUsers();

        assertEquals(1, response.size());
        assertEquals("test@example.com", response.get(0).getEmail());
    }

    @Test
    void loginUser() {

    }

    @Test
    void getUsersByTenant() {
        Authentication authentication = Mockito.mock(Authentication.class);
        when(authentication.isAuthenticated()).thenReturn(true);
        when(authentication.getName()).thenReturn("test@example.com");

        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(testUser));
        when(userService.getUsersByTenantId(1L)).thenReturn(List.of(testUser));

        ResponseEntity<?> response = userController.getUsersByTenant(authentication);

        assertEquals(200, response.getStatusCode().value());
        assertTrue(response.getBody().toString().contains("test@example.com"));
    }

//    @Test
//    void addUser() {
//        User newUser = new User();
//        newUser.setEmail("new@example.com");
//        newUser.setName("New User");
//        newUser.setPassword("pass");
//
//        Role defaultRole = new Role();
//        defaultRole.setName("USER");
//
//        when(roleRepository.findByName("USER")).thenReturn(Optional.of(defaultRole));
//
//        ResponseEntity<String> response = userController.addUser(newUser);
//
//        assertEquals(200, response.getStatusCode().value());
//        assertEquals("User registered successfully!", response.getBody());
//    }
}

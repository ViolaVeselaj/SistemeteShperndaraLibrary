package com.example.sistemeteshperndara.service;

import com.example.sistemeteshperndara.model.User;
import com.example.sistemeteshperndara.repository.UserRepository;
import com.example.sistemeteshperndara.security.CurrentUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private CurrentUser currentUser;

    @InjectMocks
    private UserService userService;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUsers() {
        User user1 = new User();
        user1.setName("Alice");

        User user2 = new User();
        user2.setName("Bob");

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<User> users = userService.getAllUsers();
        assertEquals(2, users.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void saveUser_ShouldHashPasswordAndSave() {
        User user = new User();
        user.setPassword("plainPassword");

        when(passwordEncoder.encode("plainPassword")).thenReturn("hashedPassword");

        userService.saveUser(user);

        assertEquals("hashedPassword", user.getPassword());
        verify(userRepository).save(user);
    }

    @Test
    void findByEmail_ShouldReturnUser() {
        User user = new User();
        user.setEmail("test@example.com");

        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));

        User found = userService.findByEmail("test@example.com");

        assertEquals("test@example.com", found.getEmail());
    }

    @Test
    void checkPassword_ShouldReturnTrueIfMatches() {
        when(passwordEncoder.matches("raw", "encoded")).thenReturn(true);
        boolean result = userService.checkPassword("raw", "encoded");
        assertTrue(result);
    }

    @Test
    void getCurrentUser_ShouldReturnUserFromToken() {
        User user = new User();
        user.setId(10L);

        when(currentUser.getUserIdFromToken()).thenReturn(10L);
        when(userRepository.findById(10L)).thenReturn(Optional.of(user));

        User current = userService.getCurrentUser();

        assertEquals(10L, current.getId());
    }
}

package com.example.sistemeteshperndara.service;

        import com.example.sistemeteshperndara.model.User;
        import com.example.sistemeteshperndara.repository.UserRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
        import org.springframework.stereotype.Service;
        import org.springframework.security.crypto.password.PasswordEncoder;


        import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void saveUser(User user) {
        // Hashing i fjalëkalimit përpara se ta ruajmë
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword); // Vendosni fjalëkalimin e hashuar
        userRepository.save(user); // Ruani përdoruesin me fjalëkalimin e hashuar
    }
}
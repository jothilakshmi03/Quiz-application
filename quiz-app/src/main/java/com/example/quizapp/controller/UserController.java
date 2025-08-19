package com.example.quizapp.controller;

import com.example.quizapp.model.User;
import com.example.quizapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:63342")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // Register user
    @PostMapping("/register")
    public Map<String, String> registerUser(@RequestBody RegisterRequest request) {
        Map<String, String> response = new HashMap<>();

        if (userRepository.existsByEmail(request.getEmail())) {
            response.put("message", "Email already exists");
            return response;
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setBestScore(0);
        user.setCreatedAt(new Date());

        userRepository.save(user);

        response.put("message", "User registered successfully!");
        return response;
    }

    // Login
    @PostMapping("/login")
    public Map<String, String> loginUser(@RequestBody LoginRequest request) {
        Map<String, String> response = new HashMap<>();

        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());
        if (userOpt.isEmpty() || !userOpt.get().getPassword().equals(request.getPassword())) {
            response.put("message", "Invalid credentials");
            return response;
        }

        response.put("message", "Login successful!");
        response.put("userId", userOpt.get().getId().toString());
        return response;
    }

    // Leaderboard
    @GetMapping("/leaderboard")
    public List<User> leaderboard() {
        return userRepository.findTop10ByOrderByBestScoreDesc();
    }

    // Get user by ID
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    // Request classes
    static class RegisterRequest {
        private String name;
        private String email;
        private String password;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

    static class LoginRequest {
        private String email;
        private String password;

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }
}

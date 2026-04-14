package com.example.day11.service;

import com.example.day11.repository.UserRepository;
import com.example.day11.model.User;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }
    public List<User> getAllUsers() {
        return repository.findAll();
    }
    // public String getUserById(int id) {
    //     return repository.findById(id).map(User::getEmail).orElse("User not found");
    // }
   public Optional<User> getUserById(int id) {
    return repository.findById(id);
}

    public User createUser(String email, String password) {
        User user = new User(email, password);
        return repository.save(user);
    }
    public void deleteUser(int id) {
        repository.deleteById(id);
    }
    public User updateUser(int id, User user) {
        user.setId(id);
        return repository.save(user);
    }
}

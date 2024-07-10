package com.fitness.backend.service;

import com.fitness.backend.entities.User;
import com.fitness.backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Transactional
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    @Transactional
    public User save(User user){
        return userRepository.save(user);
    }

    @Transactional
    public User update(Long id, User user) {
        User existingUser = findById(id);
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        return userRepository.save(existingUser);
    }

    @Transactional
    public void delete(Long id){
        userRepository.deleteById(id);
    }
}

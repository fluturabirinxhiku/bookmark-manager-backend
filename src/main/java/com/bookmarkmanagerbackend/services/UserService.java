package com.bookmarkmanagerbackend.services;

import com.bookmarkmanagerbackend.exceptions.CustomException;
import com.bookmarkmanagerbackend.models.User;
import com.bookmarkmanagerbackend.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthorities());
    }

    public User register(User user) {
        if (userRepository.existsByUsername((user.getUsername()))) {
            throw new CustomException("Username already exists.");
        }
        return this.save(user);
    }

    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User with id: " + id + " not found."));
    }

    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

}



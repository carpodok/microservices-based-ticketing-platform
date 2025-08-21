package com.atc.auth.service;

import com.atc.auth.dto.AuthResponse;
import com.atc.auth.dto.LoginRequest;
import com.atc.auth.dto.RegisterRequest;
import com.atc.auth.dto.UserDto;
import com.atc.auth.entity.User;
import com.atc.auth.mapper.UserMapper;
import com.atc.auth.repository.UserRepository;
import com.atc.auth.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserMapper userMapper;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.userMapper = userMapper;
    }

    public UserDto register(RegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new IllegalStateException("Username already exists");
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    public AuthResponse login(LoginRequest request) {
        Optional<User> user = userRepository.findByUsername(request.getUsername());

        return user.map(value -> new AuthResponse(jwtService.generateToken(value))).orElse(null);
    }

    public UserDto me(User user) {
        return userMapper.toDto(user);
    }
}


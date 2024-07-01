package com.hb.system.ecommerce.shoes.auth;

import java.time.LocalDateTime;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hb.system.ecommerce.shoes.entity.Role;
import com.hb.system.ecommerce.shoes.entity.User;
import com.hb.system.ecommerce.shoes.repositories.UserRepository;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        UserDetails user=userRepository.findByUsername(request.getEmail()).orElseThrow();
        return AuthResponse.builder()
            .token(jwtService.getToken(user))
            .build();
    }

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
            .name(request.getUsername())
            .username(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(Role.USER)
            .registerDate(LocalDateTime.now())
            .status(true)
            .build();
        userRepository.save(user);

        return AuthResponse.builder()
            .token(jwtService.getToken(user))
            .build();
    }

}

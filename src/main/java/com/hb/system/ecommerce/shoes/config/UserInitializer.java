package com.hb.system.ecommerce.shoes.config;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.hb.system.ecommerce.shoes.entity.User;
import com.hb.system.ecommerce.shoes.entity.Role;
import com.hb.system.ecommerce.shoes.repositories.RolRepository;
import com.hb.system.ecommerce.shoes.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class UserInitializer {
    private final PasswordEncoder passwordEncoder;
    private final RolRepository rolRepository;

    @Bean
    @Transactional
    @Order(1)
    public CommandLineRunner initRoles(RolRepository roleRepository) {
        return args -> {
            if (!roleRepository.findByName("Usuario").isPresent()) {
                Role userRole = new Role();
                userRole.setName("Usuario");
                userRole.setStatus(true);
                roleRepository.save(userRole);
            }

            if (!roleRepository.findByName("Administrador").isPresent()) {
                Role adminRole = new Role();
                adminRole.setName("Administrador");
                adminRole.setStatus(true);
                roleRepository.save(adminRole);
            }

            if (!roleRepository.findByName("Cliente").isPresent()) {
                Role clienteRole = new Role();
                clienteRole.setName("Cliente");
                clienteRole.setStatus(true);
                roleRepository.save(clienteRole);
            }
        };
    }

    
    @Bean
    @Transactional
    @Order(2)
    public CommandLineRunner initUser(UserRepository userRepository) {
        return args -> {
            Role role = rolRepository.findById(2).orElseThrow(() -> new RuntimeException("Role not found"));
            if (!userRepository.findByUsername("admin@service.com").isPresent()) {
                User user = new User();
                user.setName("Administrador");
                user.setUsername("admin@service.com");
                user.setPassword(passwordEncoder.encode("12345678"));
                user.setRegisterDate(LocalDateTime.now());
                user.setStatus(true);
                user.setRole(role);
                userRepository.save(user);
            }
        };
    }
}

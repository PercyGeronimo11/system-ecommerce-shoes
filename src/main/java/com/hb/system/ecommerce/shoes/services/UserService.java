package com.hb.system.ecommerce.shoes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hb.system.ecommerce.shoes.dto.request.UserEditReq;
import com.hb.system.ecommerce.shoes.entity.Role;
import com.hb.system.ecommerce.shoes.entity.User;
import com.hb.system.ecommerce.shoes.repositories.RolRepository;
import com.hb.system.ecommerce.shoes.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private final RolRepository roleRepository;

    public List<User> listAll() {
        // Obtener los roles "Administrador" y "Usuario"
        Optional<Role> adminRoleOptional = roleRepository.findByName("Administrador");
        Optional<Role> userRoleOptional = roleRepository.findByName("Usuario");

        // Verificar si ambos roles están presentes
        if (adminRoleOptional.isPresent() && userRoleOptional.isPresent()) {
            Role adminRole = adminRoleOptional.get();
            Role userRole = userRoleOptional.get();

            // Crear una lista con ambos roles
            List<Role> roles = List.of(adminRole, userRole);

            // Buscar usuarios con el estado true y que tengan uno de los roles especificados
            return userRepository.findUsersByStatusAndRoles(true, roles);
        } else {
            // Manejar el caso en el que uno o ambos roles no existen
            throw new RuntimeException("Roles no encontrados");
        }
    }

    public User getById(int id) {
        return userRepository.findById(id);
    }



    public void delete(int id) {
        User user = userRepository.findById(id);
        user.setStatus(false);
        userRepository.save(user);
    }

    public User update(int id, UserEditReq resource) {
        Role role = roleRepository.findById(resource.getRole())
                .orElseThrow(() -> new IllegalArgumentException("Default role not found"));
        if (userRepository.existsById(id)) {
            User user = userRepository.findById(id);
            user.setName(resource.getUsername());
            user.setUsername(resource.getEmail());
            user.setRole(role);
            if (resource.getPassword() != "") {
                user.setPassword(resource.getPassword());
            }
            return userRepository.save(user);
        } else
            return null;
    }
}
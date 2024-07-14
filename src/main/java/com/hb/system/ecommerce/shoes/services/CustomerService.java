package com.hb.system.ecommerce.shoes.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hb.system.ecommerce.shoes.entity.Customer;
import com.hb.system.ecommerce.shoes.entity.User;
import com.hb.system.ecommerce.shoes.repositories.CustomerRepository;
import com.hb.system.ecommerce.shoes.repositories.RolRepository;
import com.hb.system.ecommerce.shoes.repositories.UserRepository;
import java.sql.Date;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {
    // Inyección de dependencias de PromotionRepository
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private UserRepository userRepository;

    // Método para listar todas los clientes
    public List<Customer> listAll() {
        // Llama al método del repositorio que devuelva todos los clientes
        return customerRepository.findAll();
    }

    // Método para obtener un cliente por su ID
    public Customer getById(int id) {
        Optional<Customer> customerFind = customerRepository.findById(id);
        return customerFind.get();

    }

    // Método para guardar un nuevo cliente
    public Customer save(Customer customer) {
        Optional<User> usuarioOpt = userRepository.findById(customer.getUsuario().getId());
        User usuario;
    
        if (usuarioOpt.isPresent()) {
            usuario = usuarioOpt.get();
        } else {
            usuario = new User();
            usuario.setName(customer.getCustFirstName());
            usuario.setUsername(customer.getCustEmail());
            usuario.setPassword(customer.getCustPassword());
            LocalDateTime now = LocalDateTime.now();
            usuario.setRegisterDate(now);
            usuario.setRole(rolRepository.findById(3).get());
            usuario.setStatus(true);
            usuario = userRepository.save(usuario);
        }
        Date naci=customer.getCustBirthDate();
        customer.setCustBirthDate(naci);
        customer.setUsuario(usuario);
        customer.setCustStatus(true);
        return customerRepository.save(customer);
    }

    // Método para actualizar un cliente
    public Customer update(int idActual, Customer customerActualizado) {
        if (customerRepository.existsById(idActual)) {
            customerActualizado.setId(idActual);
    
            User usuarioActualizado = customerActualizado.getUsuario();
            Optional<User> usuarioOpt = userRepository.findById(usuarioActualizado.getId());
    
            if (usuarioOpt.isPresent()) {
                User usuarioExistente = usuarioOpt.get();
                usuarioExistente.setRole(usuarioActualizado.getRole());
    
                User usuarioGuardado = userRepository.save(usuarioExistente);
                customerActualizado.setUsuario(usuarioGuardado);

                return customerRepository.save(customerActualizado);
            } else {
                throw new RuntimeException("Usuario no encontrado");
            }
        } else {
            throw new RuntimeException("Cliente no encontrado");
        }
    }
    // Método para eliminar (desactivar) una catg
    public void delete(int idcliente) {
        Optional<Customer> categoria = customerRepository.findById(idcliente);
        categoria.get().setCustStatus(false);
        customerRepository.save(categoria.get());
    }
}

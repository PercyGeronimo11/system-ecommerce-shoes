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
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {
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
        // Guardar y retornar el cliente
        try {
            Optional<Customer> customerOpt = customerRepository.findByCustDni(customer.getCustDni());
            if (customerOpt.isPresent()) {
                throw new RuntimeException("El DNI del cliente ya existe");    
            } 
            // Crear el usuario 
             User usuar = new User();
            usuar.setName(customer.getCustFirstName());
            usuar.setUsername(customer.getCustEmail());
            usuar.setPassword(customer.getCustPassword());
            usuar.setRegisterDate(LocalDateTime.now());
            usuar.setRole(rolRepository.findById(3).get());
            usuar.setStatus(true);
            usuar = userRepository.save(usuar);
            
            // Establecer todos los campos del cliente
            customer.setCustFirstName(customer.getCustFirstName());
            customer.setCustLastName(customer.getCustLastName());
            customer.setCustDni(customer.getCustDni());
            customer.setCustDepartment(customer.getCustDepartment());
            customer.setCustBirthDate(customer.getCustBirthDate());
            customer.setCustCity(customer.getCustCity());
            customer.setCustProvince(customer.getCustProvince());
            customer.setCustPassword(customer.getCustPassword());
            customer.setCustCellphone(customer.getCustCellphone());
            customer.setCustStatus(true);
            customer.setUsuario(usuar);
            return customerRepository.save(customer);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el cliente: " + e.getMessage(), e);
        }

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

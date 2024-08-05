package com.hb.system.ecommerce.shoes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hb.system.ecommerce.shoes.dto.ApiResponse;
import com.hb.system.ecommerce.shoes.entity.Customer;
import com.hb.system.ecommerce.shoes.exceptions.CustomException;
import com.hb.system.ecommerce.shoes.services.CustomerService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    private  CustomerService customerService;
    @GetMapping
    public ResponseEntity<ApiResponse<List<Customer>>> list() {
        List<Customer> customers = customerService.listAll();
        ApiResponse<List<Customer>> response= new ApiResponse<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Lista de clientes exitosamente");
        response.setData(customers);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Customer>> create(@RequestBody Customer request) {
        ApiResponse<Customer> response = new ApiResponse<>();
        try {
            Customer customer = customerService.save(request);
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("Se registró un cliente exitosamente");
            response.setData(customer);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (CustomException e) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Customer>> edit(@PathVariable int id, @RequestBody Customer customerRequest){
        Customer customer= customerService.update(id,customerRequest);
        ApiResponse<Customer> response=new ApiResponse<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("el cliente se actualizó exitosamente");
        response.setData(customer);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Customer>> getById(@PathVariable int id){
        Customer customer= customerService.getById(id);
        ApiResponse<Customer> response=new ApiResponse<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Detalle del customer recuperado exitossamente");
        response.setData(customer);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<ApiResponse<Customer>> getByDni(@PathVariable String dni){
        Customer customer= customerService.getByDni(dni);
        ApiResponse<Customer> response=new ApiResponse<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Cliente Obtenido por Dni");
        response.setData(customer);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/username/{name}")
    public ResponseEntity<ApiResponse<List<Customer>>> findByUsername(@PathVariable String name){
        List<Customer> customers = customerService.getByName(name);
        ApiResponse<List<Customer>> response = new ApiResponse<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Clientes obtenidos por nombre de usuario");
        response.setData(customers);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Customer>> delete(@PathVariable int id){
        customerService.delete(id);
        ApiResponse<Customer> response=new ApiResponse<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("customer eliminada exitosamente");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}

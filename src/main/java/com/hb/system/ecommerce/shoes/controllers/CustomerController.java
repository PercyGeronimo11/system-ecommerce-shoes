package com.hb.system.ecommerce.shoes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hb.system.ecommerce.shoes.dto.ApiResponse;
import com.hb.system.ecommerce.shoes.entity.Customer;
import com.hb.system.ecommerce.shoes.services.CustomerService;

import java.util.List;

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
    public ResponseEntity<ApiResponse<Customer>> create(@RequestBody Customer Request){
        Customer customer= customerService.save(Request);
        ApiResponse<Customer> response= new ApiResponse<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Se registró un cliente exitosamente");
        response.setData(customer);
        return new ResponseEntity<>(response,HttpStatus.OK);
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

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Customer>> delete(@PathVariable int id){
        customerService.delete(id);
        ApiResponse<Customer> response=new ApiResponse<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("customer eliminada exitosamente");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}

package com.hb.system.ecommerce.shoes.services;

import org.springframework.stereotype.Service;

import com.hb.system.ecommerce.shoes.entity.Department;
import com.hb.system.ecommerce.shoes.repositories.DepartmentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartamentService {

    private final DepartmentRepository departmentRepository;

    public java.util.List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    } 
    
    public java.util.Optional<Department> getDepartmentById(int idDepa) {
        return departmentRepository.findById(idDepa);
    }
}

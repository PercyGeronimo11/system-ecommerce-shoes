package com.hb.system.ecommerce.shoes.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hb.system.ecommerce.shoes.dto.ApiResponse;
import com.hb.system.ecommerce.shoes.entity.Department;
import com.hb.system.ecommerce.shoes.services.DepartamentService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/department")
@RequiredArgsConstructor
@RestControllerAdvice
public class DepartmentController {

    private final DepartamentService departmentService;

    @GetMapping 
    public  ResponseEntity<ApiResponse<List<Department>>> getAllDepartaments(){
        List<Department> departments = departmentService.getAllDepartments();
        ApiResponse<List<Department>> response= new ApiResponse<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Listado de departamentos exitoso");
        response.setData(departments);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }


    @GetMapping("/{idDepa}")
    public  java.util.Optional<Department> getByDepartment(@PathVariable Integer idDepa){
        return departmentService.getDepartmentById(idDepa);
    }

}

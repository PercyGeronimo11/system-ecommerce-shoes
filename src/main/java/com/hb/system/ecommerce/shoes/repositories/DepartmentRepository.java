package com.hb.system.ecommerce.shoes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hb.system.ecommerce.shoes.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}

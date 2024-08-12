package com.hb.system.ecommerce.shoes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hb.system.ecommerce.shoes.entity.Province;

public interface ProvinceRepository extends JpaRepository<Province, Integer> {
    List<Province> findByDepartment_IdDepa(Integer idDepa);

}

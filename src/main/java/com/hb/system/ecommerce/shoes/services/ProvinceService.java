package com.hb.system.ecommerce.shoes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hb.system.ecommerce.shoes.entity.Province;
import com.hb.system.ecommerce.shoes.repositories.ProvinceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProvinceService {

        private final ProvinceRepository provinceRepository;


    public List<Province> getAllProvinces() {
        return provinceRepository.findAll();
    }
    
    public Optional<Province> getProvinceById(Integer idProv) {
        return provinceRepository.findById(idProv);
    }

    public List<Province> getProvincesByDepartmentId(Integer idDepa) {
        return provinceRepository.findByDepartment_IdDepa(idDepa);
    }

}

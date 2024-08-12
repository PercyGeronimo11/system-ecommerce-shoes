package com.hb.system.ecommerce.shoes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hb.system.ecommerce.shoes.entity.District;
import com.hb.system.ecommerce.shoes.repositories.DistrictRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DistrictService {
 
    private final DistrictRepository districtRepository;

    public List<District> getAllDistricts() {
        return districtRepository.findAll();
    }

    public Optional<District> getDistrictById(Integer idDist) {
        return districtRepository.findById(idDist);
    }

    public List<District> getDistrictsByProvinceId(Integer idProv) {
        return districtRepository.findByProvince_IdProv(idProv);
    }
}

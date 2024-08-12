package com.hb.system.ecommerce.shoes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hb.system.ecommerce.shoes.entity.District;

public interface DistrictRepository extends JpaRepository<District, Integer>{
    // Método para encontrar distritos por id de provincia
    List<District> findByProvince_IdProv(Integer idProv);
}

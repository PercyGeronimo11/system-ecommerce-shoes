package com.hb.system.ecommerce.shoes.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hb.system.ecommerce.shoes.dto.ApiResponse;
import com.hb.system.ecommerce.shoes.entity.District;
import com.hb.system.ecommerce.shoes.services.DistrictService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/district")
@RequiredArgsConstructor
@RestControllerAdvice
public class DistrictController {
   private final DistrictService districtService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<District>>> getAllDistricts() {
        List<District> districts = districtService.getAllDistricts();
        ApiResponse<List<District>> response = new ApiResponse<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Listado de distritos exitoso");
        response.setData(districts);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{idDist}")
    public ResponseEntity<ApiResponse<Optional<District>>> getDistrictById(@PathVariable Integer idDist) {
        Optional<District> district = districtService.getDistrictById(idDist);
        ApiResponse<Optional<District>> response = new ApiResponse<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Distrito encontrado");
        response.setData(district);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/province/{idProv}")
    public ResponseEntity<ApiResponse<List<District>>> getDistrictsByProvinceId(@PathVariable Integer idProv) {
        List<District> districts = districtService.getDistrictsByProvinceId(idProv);
        ApiResponse<List<District>> response = new ApiResponse<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Listado de distritos por provincia exitoso");
        response.setData(districts);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

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
import com.hb.system.ecommerce.shoes.entity.Province;
import com.hb.system.ecommerce.shoes.services.ProvinceService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/province")
@RequiredArgsConstructor
@RestControllerAdvice
public class ProvinceController {

        private final ProvinceService provinceService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Province>>> getAllProvinces() {
        List<Province> provinces = provinceService.getAllProvinces();
        ApiResponse<List<Province>> response = new ApiResponse<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Listado de provincias exitoso");
        response.setData(provinces);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{idProv}")
    public ResponseEntity<ApiResponse<Optional<Province>>> getProvinceById(@PathVariable Integer idProv) {
        Optional<Province> province = provinceService.getProvinceById(idProv);
        ApiResponse<Optional<Province>> response = new ApiResponse<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Provincia encontrada");
        response.setData(province);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/department/{idDepa}")
    public ResponseEntity<ApiResponse<List<Province>>> getProvincesByDepartmentId(@PathVariable Integer idDepa) {
        List<Province> provinces = provinceService.getProvincesByDepartmentId(idDepa);
        ApiResponse<List<Province>> response = new ApiResponse<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Listado de provincias por departamento exitoso");
        response.setData(provinces);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

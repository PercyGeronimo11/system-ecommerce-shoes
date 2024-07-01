package com.hb.system.ecommerce.shoes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hb.system.ecommerce.shoes.dto.ApiResponse;
import com.hb.system.ecommerce.shoes.entity.Promotion;
import com.hb.system.ecommerce.shoes.services.PromotionService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*") // Permitir solicitudes desde cualquier origen
@RequestMapping("/api/promotion")
public class PromotionController {
    @Autowired
    private PromotionService promotionService;


    @GetMapping
    public ResponseEntity<ApiResponse<List<Promotion>>> list() {
        List<Promotion> promotions = promotionService.listAll();
        ApiResponse<List<Promotion>> response= new ApiResponse<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Lista de promociones exitosamente");
        response.setData(promotions);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<ApiResponse<Promotion>> create(@RequestBody Promotion promotionRequest){
        Promotion promocion= promotionService.save(promotionRequest);
        ApiResponse<Promotion> response= new ApiResponse<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Se registró una promocion exitosamente");
        response.setData(promocion);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Promotion>> edit(@PathVariable int id, @RequestBody Promotion promotionRequest){
        Promotion promocion= promotionService.update(id,promotionRequest);
        ApiResponse<Promotion> response=new ApiResponse<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("La promocion se actualizó exitosamente");
        response.setData(promocion);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Promotion>> getById(@PathVariable int id){
        Promotion promocion= promotionService.getById(id);
        ApiResponse<Promotion> response=new ApiResponse<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Detalle de la promocion recuperado exitossamente");
        response.setData(promocion);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Promotion>> delete(@PathVariable int id){
        promotionService.delete(id);
        ApiResponse<Promotion> response=new ApiResponse<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Promocion eliminada exitosamente");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
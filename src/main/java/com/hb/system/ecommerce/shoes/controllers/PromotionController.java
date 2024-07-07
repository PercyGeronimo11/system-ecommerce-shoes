package com.hb.system.ecommerce.shoes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import com.hb.system.ecommerce.shoes.dto.ApiResponse;
import com.hb.system.ecommerce.shoes.entity.Promotion;
import com.hb.system.ecommerce.shoes.services.PromotionService;
import java.io.IOException;
import java.util.List;
import org.springframework.http.HttpHeaders;
@RestController
@CrossOrigin(origins = "*") // Permitir solicitudes desde cualquier origen
@RequestMapping("/api/promotion")
public class PromotionController {
    @Autowired
    private PromotionService promotionService;
    @GetMapping
    public ResponseEntity<ApiResponse<List<Promotion>>> list() {
        List<Promotion> promotions = promotionService.listAll();
        ApiResponse<List<Promotion>> response = new ApiResponse<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Lista de promociones exitosamente");
        response.setData(promotions);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Promotion>> create(@RequestPart("promotion") Promotion promotionRequest,
            @RequestPart("file") MultipartFile file) {
        Promotion promocion;
        try {
            promocion = promotionService.save(promotionRequest, file);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (IllegalArgumentException e) {
            ApiResponse<Promotion> response = new ApiResponse<>();
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        ApiResponse<Promotion> response = new ApiResponse<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Se registró una promoción exitosamente");
        response.setData(promocion);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Promotion>> edit(@PathVariable int id,
            @RequestPart("promotion") Promotion promotionRequest, @RequestPart("file") MultipartFile file) {
        Promotion promocion;
        try {
            promocion = promotionService.update(id, promotionRequest, file);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (IllegalArgumentException e) {
            ApiResponse<Promotion> response = new ApiResponse<>();
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        ApiResponse<Promotion> response = new ApiResponse<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("La promoción se actualizó exitosamente");
        response.setData(promocion);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Promotion>> getById(@PathVariable int id) {
        Promotion promocion = promotionService.getById(id);
        ApiResponse<Promotion> response = new ApiResponse<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Detalle de la promocion recuperado exitossamente");
        response.setData(promocion);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Promotion>> delete(@PathVariable int id) {
        promotionService.delete(id);
        ApiResponse<Promotion> response = new ApiResponse<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Promocion eliminada exitosamente");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/images/{fileName:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String fileName) {
        Resource resource;
        try {
            resource = promotionService.loadFileAsResource(fileName);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
package com.hb.system.ecommerce.shoes.controllers;

import com.hb.system.ecommerce.shoes.dto.ApiResponse;
import com.hb.system.ecommerce.shoes.dto.request.LotCreateReq;
import com.hb.system.ecommerce.shoes.dto.request.LotEditReq;
import com.hb.system.ecommerce.shoes.dto.response.LotListResp;
import com.hb.system.ecommerce.shoes.entity.Lot;
import com.hb.system.ecommerce.shoes.entity.Lot;
import com.hb.system.ecommerce.shoes.repositories.LotRepository;
import com.hb.system.ecommerce.shoes.services.LotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/lot")
public class LotController {
    @Autowired
    private LotService lotService;

    @GetMapping(
            value = {"/list"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<LotListResp>> index(@RequestParam(name = "search") String search){
        LotListResp lotListResp = lotService.lotListService(search);
        ApiResponse<LotListResp> response = new ApiResponse<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Lista de lotes exitosamente");
        response.setData(lotListResp);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(
            value = "/store",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<Lot>> storeController(@RequestBody LotCreateReq lotCreateReq) throws IOException {
        Lot lot= lotService.lotStoreService(lotCreateReq);
        ApiResponse<Lot> response= new ApiResponse<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Se a guardado el loto exitosamente");
        response.setData(lot);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ApiResponse<Lot>> editLot(@PathVariable int id,
                                                            @RequestBody LotEditReq lotEditReq){
        Lot lot= lotService.lotEditService(id,lotEditReq);
        ApiResponse<Lot> response=new ApiResponse<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("El loto ha sido editado exitosamente");
        response.setData(lot);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @Value("${image.upload.directory}")
    private String uploadDir;

    @GetMapping("/images/{imageName:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String imageName) {
        Path imagePath = Paths.get(uploadDir).resolve(imageName);
        Resource imageResource;
        try {
            imageResource = new UrlResource(imagePath.toUri());
            if (imageResource.exists() || imageResource.isReadable()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG) // O el tipo de imagen correcto
                        .body(imageResource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

}



package com.hb.system.ecommerce.shoes.controllers;
import com.hb.system.ecommerce.shoes.dto.ApiResponse;
import com.hb.system.ecommerce.shoes.dto.request.ProductCreateReq;
import com.hb.system.ecommerce.shoes.dto.request.ProductEditReq;
import com.hb.system.ecommerce.shoes.dto.request.ProductListReq;
import com.hb.system.ecommerce.shoes.dto.response.ProductListResp;
import com.hb.system.ecommerce.shoes.entity.Product;

import com.hb.system.ecommerce.shoes.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins = "*")
@RestController
@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping(
            value = {"/index", "/", "/list"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<ProductListResp>> index( ProductListReq productListReq){
        ProductListResp productListResp = productService.productListService(productListReq);
        ApiResponse<ProductListResp> response = new ApiResponse<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Lista de productos exitosamente");
        response.setData(productListResp);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(
            value = "/store",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<Product>> create( ProductCreateReq productCreateReq,
                                                        @RequestParam(name = "file", required = false) MultipartFile file) throws IOException {
        Product product= productService.productStoreService(productCreateReq, file);
        ApiResponse<Product> response= new ApiResponse<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Se a guardado el producto exitosamente");
        response.setData(product);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }


    @PutMapping("/edit/{id}")
    public ResponseEntity<ApiResponse<Product>> editProduct(@PathVariable int id,
                                                            @RequestBody ProductEditReq productEditReq,
                                                            @RequestParam("file") MultipartFile file){
       Product product= productService.productEditService(id,productEditReq,file);
       ApiResponse<Product> response=new ApiResponse<>();
       response.setStatus(HttpStatus.OK.value());
       response.setMessage("El producto ha sido editado exitosamente");
       response.setData(product);
       return new ResponseEntity<>(response,HttpStatus.OK);
    }

//    @GetMapping({"/index","/","list"})
//    public String index(Model model){
//        List<Product> productList=productRepository.findAll();
//        model.addAttribute("products", productList);
//        model.addAttribute("contenido", "products/list");
//        return "layout/index";
//    }

//    @GetMapping("/create")
//    public String create(Model model){
//        model.addAttribute("product",new Product());
//        model.addAttribute("contenido", "products/create");
//        return "layout/index";
//    }

//    @PostMapping("/save")
//    public String save(@ModelAttribute Product product){
//        productRepository.save(product);
//        return "redirect:/product/index";
//    }

//    @PostMapping("/update")
//    public String updateProduct(@ModelAttribute Product product){
//        Optional<Product> productFind=productRepository.findById(product.getId());
//        if (productFind.isPresent()){
//            productFind.get().setCategory(product.getCategory());
//            productFind.get().setPro_name(product.getPro_name());
//            productFind.get().setPro_description(product.getPro_description());
//            productFind.get().setPro_unit_price(product.getPro_unit_price());
//            productRepository.save(productFind.get());
//        }else{
//
//        }
//        return "redirect:/product/index";
//    }
//
//    @GetMapping("/cancel")
//    public String cancel(){
//        return "redirect:/product/index";
//    }
//
//    @GetMapping("/delete/{id}")
//    public String delete(@PathVariable int id){
//        productRepository.deleteById(id);
//        return "redirect:/product/index";
//    }
}



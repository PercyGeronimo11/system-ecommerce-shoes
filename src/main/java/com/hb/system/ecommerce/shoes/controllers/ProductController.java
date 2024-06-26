package com.hb.system.ecommerce.shoes.controllers;
import com.hb.system.ecommerce.shoes.dto.ApiResponse;
import com.hb.system.ecommerce.shoes.dto.request.ProductCreateReq;
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
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<Product>> create(@RequestBody ProductCreateReq productCreateReq){
        Product product= productService.productStoreService(productCreateReq);
        ApiResponse<Product> response= new ApiResponse<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Se a guardado el producto exitosamente");
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

//
//    @PostMapping("/save")
//    public String save(@ModelAttribute Product product){
//        productRepository.save(product);
//        return "redirect:/product/index";
//    }
//
//    @GetMapping("/edit/{id}")
//    public String editProduct(Model model,@PathVariable int id){
//        Optional<Product> productFind=productRepository.findById(id);
//        if(productFind.isEmpty()){
//            return "redirect:/product/index";
//        }else{
//            model.addAttribute("product",productFind.get());
//            model.addAttribute("contenido", "products/edit");
//            return "layout/index";
//        }
//    }
//
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



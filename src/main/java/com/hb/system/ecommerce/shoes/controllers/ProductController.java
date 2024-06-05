package com.hb.system.ecommerce.shoes.controllers;
import com.hb.system.ecommerce.shoes.entity.Product;
import com.hb.system.ecommerce.shoes.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping({"/index","/"})
    public String index(Model model){
        List<Product> productList=productRepository.findAll();
        model.addAttribute("products", productList);
        return "products/index";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("product",new Product());
        return "products/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Product product){
        productRepository.save(product);
        return "redirect:/product/index";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(Model model,@PathVariable int id){
        Optional<Product> productFind=productRepository.findById(id);
        if(productFind.isEmpty()){
            return "redirect:/product/index";
        }else{
            model.addAttribute("product",productFind.get());
            return "products/edit";
        }
    }

    @PostMapping("/update")
    public String updateProduct(@ModelAttribute Product product){
        productRepository.save(product);
        return "redirect:/product/index";
    }

    @GetMapping("/cancel")
    public String cancel(){
        return "redirect:/product/index";
    }
}



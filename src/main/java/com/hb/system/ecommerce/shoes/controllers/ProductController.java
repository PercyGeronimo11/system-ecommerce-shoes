package com.hb.system.ecommerce.shoes.controllers;
import com.hb.system.ecommerce.shoes.entity.Product;
import com.hb.system.ecommerce.shoes.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
    public String create(){
        return "products/create";
    }

/*
    @PostMapping("/save")
    public String save(){
        return "products/save";
    }

    @GetMapping("/edit")
    public String edit(){
        return "products/save";
    }
    */

}



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

    @GetMapping({"/index","/","list"})
    public String index(Model model){
        List<Product> productList=productRepository.findAll();
        model.addAttribute("products", productList);
        model.addAttribute("contenido", "products/list");
        return "layout/index";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("product",new Product());
        model.addAttribute("contenido", "products/create");
        return "layout/index";
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
            model.addAttribute("contenido", "products/edit");
            return "layout/index";
        }
    }

    @PostMapping("/update")
    public String updateProduct(@ModelAttribute Product product){
        Optional<Product> productFind=productRepository.findById(product.getId());
        if (productFind.isPresent()){
            productFind.get().setCategory(product.getCategory());
            productFind.get().setPro_name(product.getPro_name());
            productFind.get().setPro_description(product.getPro_description());
            productFind.get().setPro_unit_price(product.getPro_unit_price());
            productRepository.save(productFind.get());
        }else{

        }
        return "redirect:/product/index";
    }

    @GetMapping("/cancel")
    public String cancel(){
        return "redirect:/product/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        productRepository.deleteById(id);
        return "redirect:/product/index";
    }
}



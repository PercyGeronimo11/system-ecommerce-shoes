package com.hb.system.ecommerce.shoes;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ui.Model;

@SpringBootApplication
@Controller
public class DemoApplication {
  @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("contenido", "dashboard");
        return "layout/index";
    }
}

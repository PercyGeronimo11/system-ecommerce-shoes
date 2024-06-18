package com.hb.system.ecommerce.shoes;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class DemoApplication {
  @GetMapping("/")
    public String index() {
        return "layout/index";
    }
}

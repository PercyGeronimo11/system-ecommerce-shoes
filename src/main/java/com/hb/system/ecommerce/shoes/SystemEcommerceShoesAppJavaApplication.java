package com.hb.system.ecommerce.shoes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

//@SpringBootApplication
@ComponentScan(basePackages = "com.hb.system.ecommerce.shoes")
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class SystemEcommerceShoesAppJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SystemEcommerceShoesAppJavaApplication.class, args);

    }
}

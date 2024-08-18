package com.hb.system.ecommerce.shoes.DataIniatilizer;

import com.hb.system.ecommerce.shoes.entity.Category;
import com.hb.system.ecommerce.shoes.entity.Product;
import com.hb.system.ecommerce.shoes.repositories.CategoryRepository;
import com.hb.system.ecommerce.shoes.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;


@Configuration
@RequiredArgsConstructor
public class ProductInitializer {
    private  final ProductRepository productRepository;
    private  final CategoryRepository categoryRepository;

    @Bean
    @Transactional
    @Order(7)
    public CommandLineRunner initProducts() {
        return args -> {
            if (categoryRepository.count() == 0) {
                // Inicializar categorías
                Category casuales = categoryRepository.save(new Category(1, "Casuales", "Cuero 100% original", true, false));
                Category sandalias = categoryRepository.save(new Category(2, "Sandalias", "Cuero 100% original", true, true));
                Category vestir = categoryRepository.save(new Category(3, "De vestir", "Cuero 100% original", true, true));
                Category botas = categoryRepository.save(new Category(4, "Botas", "Cuero 100% original", true, true));
                Category ballerinas = categoryRepository.save(new Category(5, "Ballerinas", "Cuero 100% original", true, false));

                // Inicializar productos
                productRepository.save(new Product(1, casuales, "Producto 1", "Descripción 1", 49.99, BigDecimal.valueOf(30.00), "33", null, null, "http://127.0.0.1:8080/product/images/zapato-negro.png", "Negro", 10, 4, true));
                productRepository.save(new Product(2, sandalias, "Producto 2", "Descripción 2", 39.99, BigDecimal.valueOf(25.00), "35", "Platform2", "Taco2", "http://127.0.0.1:8080/product/images/zapato-negro.png", "Blanco", 15, 5, true));
                productRepository.save(new Product(3, vestir, "Producto 3", "Descripción 3", 59.99, BigDecimal.valueOf(40.00), "38", "Platform3", "Taco3", "http://127.0.0.1:8080/product/images/zapato-negro.png", "Rojo", 8, 3, true));
                productRepository.save(new Product(4, botas, "Producto 4", "Descripción 4", 69.99, BigDecimal.valueOf(45.00), "40", "Platform4", "Taco4", "http://127.0.0.1:8080/product/images/zapato-negro.png", "Marrón", 12, 4, true));
                productRepository.save(new Product(5, ballerinas, "Producto 5", "Descripción 5", 29.99, BigDecimal.valueOf(20.00), "33", null, null, "http://127.0.0.1:8080/product/images/zapato-negro.png", "Azul", 20, 2, true));
                productRepository.save(new Product(6, casuales, "Producto 6", "Descripción 6", 49.99, BigDecimal.valueOf(30.00), "33", null, null, "http://127.0.0.1:8080/product/images/zapato-negro.png", "Verde", 11, 4, true));
                productRepository.save(new Product(7, sandalias, "Producto 7", "Descripción 7", 39.99, BigDecimal.valueOf(25.00), "33", "Platform2", "Taco2", "http://127.0.0.1:8080/product/images/zapato-negro.png", "Amarillo", 14, 5, true));
                productRepository.save(new Product(8, vestir, "Producto 8", "Descripción 8", 59.99, BigDecimal.valueOf(40.00), "35", "Platform3", "Taco3", "http://127.0.0.1:8080/product/images/zapato-negro.png", "Rosa", 9, 3, true));
                productRepository.save(new Product(9, botas, "Producto 9", "Descripción 9", 69.99, BigDecimal.valueOf(45.00), "38", "Platform4", "Taco4", "http://127.0.0.1:8080/product/images/zapato-negro.png", "Negro", 7, 4, true));
                productRepository.save(new Product(10, ballerinas, "Producto 10", "Descripción 10", 29.99, BigDecimal.valueOf(20.00), "40", null, null, "http://127.0.0.1:8080/product/images/zapato-negro.png", "Blanco", 18, 4, true));
                productRepository.save(new Product(11, casuales, "Producto 11", "Descripción 11", 49.99, BigDecimal.valueOf(30.00), "40", null, null, "http://127.0.0.1:8080/product/images/zapato-negro.png", "Rojo", 13, 3, true));
                productRepository.save(new Product(12, sandalias, "Producto 12", "Descripción 12", 39.99, BigDecimal.valueOf(25.00), "33", "Platform2", "Taco2", "http://127.0.0.1:8080/product/images/zapato-negro.png", "Azul", 16, 4, true));
                productRepository.save(new Product(13, vestir, "Producto 13", "Descripción 13", 59.99, BigDecimal.valueOf(40.00), "35", "Platform3", "Taco3", "http://127.0.0.1:8080/product/images/zapato-negro.png", "Verde", 10, 2, true));
                productRepository.save(new Product(14, botas, "Producto 14", "Descripción 14", 69.99, BigDecimal.valueOf(45.00), "38", "Platform4", "Taco4", "http://127.0.0.1:8080/product/images/zapato-negro.png", "Amarillo", 5, 5, true));
                productRepository.save(new Product(15, ballerinas, "Producto 15", "Descripción 15", 29.99, BigDecimal.valueOf(20.00), "38", null, null, "http://127.0.0.1:8080/product/images/zapato-negro.png", "Rosa", 19, 4, true));
                productRepository.save(new Product(16, casuales, "Producto 16", "Descripción 16", 49.99, BigDecimal.valueOf(30.00), "40", null, null,"http://127.0.0.1:8080/product/images/zapato-negro.png", "Marrón", 11, 4, true));
                productRepository.save(new Product(17, sandalias, "Producto 17", "Descripción 17", 39.99, BigDecimal.valueOf(25.00), "40", "Platform2", "Taco2", "http://127.0.0.1:8080/product/images/zapato-negro.png", "Negro", 9, 3, true));
                productRepository.save(new Product(18, vestir, "Producto 18", "Descripción 18", 59.99, BigDecimal.valueOf(40.00), "40", "Platform3", "Taco3", "http://127.0.0.1:8080/product/images/zapato-negro.png", "Blanco", 6, 4, true));
                productRepository.save(new Product(19, botas, "Producto 19", "Descripción 19", 69.99, BigDecimal.valueOf(45.00), "40", "Platform4", "Taco4", "http://127.0.0.1:8080/product/images/zapato-negro.png", "Azul", 7, 4, true));
                productRepository.save(new Product(20, ballerinas, "Producto 20", "Descripción 20", 29.99, BigDecimal.valueOf(20.00), "40", null, null, "http://127.0.0.1:8080/product/images/zapato-negro.png", "Rojo", 8, 5, true));
            }
        };
    }
}

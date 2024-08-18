//package com.hb.system.ecommerce.shoes.DataIniatilizer;
//
//import com.hb.system.ecommerce.shoes.entity.Category;
//import com.hb.system.ecommerce.shoes.repositories.CategoryRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.transaction.annotation.Transactional;
//
//
//@Configuration
//@RequiredArgsConstructor
//public class CategoryInitializer {
//    private  final CategoryRepository categoryRepository;
//
//    @Bean
//    @Transactional
//    @Order(7)
//    public CommandLineRunner initCategories() {
//        return args -> {
//            if (categoryRepository.count() == 0) {
//                categoryRepository.save(new Category(1,
//                        "Casuales",
//                        "Cuero 100% original",
//                        true,
//                        false));
//
//                categoryRepository.save(new Category(1,
//                        "Sandalias",
//                        "Cuero 100% original",
//                        true,
//                        true));
//                categoryRepository.save(new Category(1,
//                        "De vestir",
//                        "Cuero 100% original",
//                        true,
//                        true));
//                categoryRepository.save(new Category(1,
//                        "Botas",
//                        "Cuero 100% original",
//                        true,
//                        true));
//                categoryRepository.save(new Category(1,
//                        "Ballerianas",
//                        "Cuero 100% original",
//                        true,
//                        false));
//            }
//        };
//    }
//}

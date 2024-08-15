package com.hb.system.ecommerce.shoes.DataIniatilizer;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import com.hb.system.ecommerce.shoes.entity.Department;
import com.hb.system.ecommerce.shoes.repositories.DepartmentRepository;
import org.springframework.transaction.annotation.Transactional;



@Configuration
@RequiredArgsConstructor
public class DepartmentInitializer {
    private  final DepartmentRepository departmentRepository;

    @Bean
    @Transactional
    @Order(4)
    public CommandLineRunner initDepartments() {
        return args -> {
            if (departmentRepository.count() == 0) {
                departmentRepository.save(new Department("AMAZONAS"));
                departmentRepository.save(new Department("ANCASH"));
                departmentRepository.save(new Department("APURIMAC"));
                departmentRepository.save(new Department("AREQUIPA"));
                departmentRepository.save(new Department("AYACUCHO"));
                departmentRepository.save(new Department("CAJAMARCA"));
                departmentRepository.save(new Department("CALLAO"));
                departmentRepository.save(new Department("CUSCO"));
                departmentRepository.save(new Department("HUANCAVELICA"));
                departmentRepository.save(new Department("HUANUCO"));
                departmentRepository.save(new Department("ICA"));
                departmentRepository.save(new Department("JUNIN"));
                departmentRepository.save(new Department("LA LIBERTAD"));
                departmentRepository.save(new Department("LAMBAYEQUE"));
                departmentRepository.save(new Department("LIMA"));
                departmentRepository.save(new Department("LORETO"));
                departmentRepository.save(new Department("MADRE DE DIOS"));
                departmentRepository.save(new Department("MOQUEGUA"));
                departmentRepository.save(new Department("PASCO"));
                departmentRepository.save(new Department("PIURA"));
                departmentRepository.save(new Department("PUNO"));
                departmentRepository.save(new Department("SAN MARTIN"));
                departmentRepository.save(new Department("TACNA"));
                departmentRepository.save(new Department("TUMBES"));
                departmentRepository.save(new Department("UCAYALI"));
            }
        };
    }
}

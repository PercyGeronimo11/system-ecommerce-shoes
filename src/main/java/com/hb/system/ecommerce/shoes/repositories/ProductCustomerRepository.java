package com.hb.system.ecommerce.shoes.repositories;

import com.hb.system.ecommerce.shoes.entity.Customer;
import com.hb.system.ecommerce.shoes.entity.Product;
import com.hb.system.ecommerce.shoes.entity.ProductCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductCustomerRepository extends JpaRepository<ProductCustomer,Integer> {
    List<ProductCustomer> findByCustomer(Customer customer);
    Optional<ProductCustomer> findByCustomer_IdAndProduct_Id(int customerId, int productId);
    List<ProductCustomer> findByProduct(Product product);
}

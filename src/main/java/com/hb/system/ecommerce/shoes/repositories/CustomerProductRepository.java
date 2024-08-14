package com.hb.system.ecommerce.shoes.repositories;

import com.hb.system.ecommerce.shoes.entity.CustomerProduct;
import com.hb.system.ecommerce.shoes.entity.LotDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CustomerProductRepository extends JpaRepository<CustomerProduct,Integer> {

}

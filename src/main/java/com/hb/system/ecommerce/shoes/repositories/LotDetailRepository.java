package com.hb.system.ecommerce.shoes.repositories;

import com.hb.system.ecommerce.shoes.entity.LotDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface LotDetailRepository extends JpaRepository<LotDetail,Integer> {
    void deleteByLot_Id(int lotId);
}

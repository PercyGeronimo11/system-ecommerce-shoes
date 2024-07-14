package com.hb.system.ecommerce.shoes.services.impl;

import com.hb.system.ecommerce.shoes.entity.LotDetail;
import com.hb.system.ecommerce.shoes.repositories.LotDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class LotDetailServiceImpl {
    @Autowired
    private LotDetailRepository lotDetailRepository;

    public List<LotDetail> lotDetailsGetService(int id) {
        List<LotDetail> lotDetails= lotDetailRepository.findAllByLot_id(id);
        return lotDetails;
    }

}

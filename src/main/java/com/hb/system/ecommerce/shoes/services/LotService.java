package com.hb.system.ecommerce.shoes.services;

import com.hb.system.ecommerce.shoes.dto.request.LotCreateReq;
import com.hb.system.ecommerce.shoes.dto.request.LotEditReq;
import com.hb.system.ecommerce.shoes.dto.response.LotListResp;
import com.hb.system.ecommerce.shoes.entity.Lot;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface LotService {
    LotListResp lotListService(String search);
    Lot lotStoreService(LotCreateReq lotCreateReq)throws IOException;
    Lot lotEditService(int id,LotEditReq lotEditReq);
}

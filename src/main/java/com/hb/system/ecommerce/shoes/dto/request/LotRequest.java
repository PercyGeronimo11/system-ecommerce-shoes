package com.hb.system.ecommerce.shoes.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class LotRequest {
    private int id;
    private int productId;
    private List<LotDetailRequest> lotDetail;
    private String lotQuantityProducts;
    private String lotTotalCost;
}

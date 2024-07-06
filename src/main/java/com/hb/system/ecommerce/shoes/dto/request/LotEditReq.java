package com.hb.system.ecommerce.shoes.dto.request;

import lombok.Data;

@Data
public class LotEditReq {
    private int categoryId;
    private int productId;
    private String lotTotalCost;
    private String lotProductsAmount;
    private String proDescription;
    private String proName;
    private String proSizePlatform;
    private String proSizeTacon;
    private String proStock;
}

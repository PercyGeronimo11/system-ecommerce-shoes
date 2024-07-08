package com.hb.system.ecommerce.shoes.dto.request;

import lombok.Data;

@Data
public class ProductEditReq {
    private int catId;
    private String proName;
    private String proDescription;
    private String proSizePlatform;
    private String proSizeTaco;
    private Double proUnitPrice;
    private Double proUnitCost;
    private String proSize;
    private String proColor;
    private int proStock;
    private Boolean proStatus;
}

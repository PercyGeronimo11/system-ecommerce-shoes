package com.hb.system.ecommerce.shoes.dto.request;

import lombok.Data;

@Data
public class ProductEditReq {
    private int catId;
    private String proName;
    private String proDescription;
    private String proUnitPrice;
    private String proSizePlatform;
    private String proSizeTaco;
}

package com.hb.system.ecommerce.shoes.dto.request;

import com.hb.system.ecommerce.shoes.entity.Category;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class ProductCreateReq {
    private int catId;
    private String proName;
    private String proDescription;
    private String proSizePlatform;
    private String proSizeTaco;
    private Double proUnitPrice;
    private String proSize;
    private String proColor;
}

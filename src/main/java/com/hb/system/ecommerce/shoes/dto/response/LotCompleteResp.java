package com.hb.system.ecommerce.shoes.dto.response;

import com.hb.system.ecommerce.shoes.entity.Lot;
import com.hb.system.ecommerce.shoes.entity.LotDetail;
import com.hb.system.ecommerce.shoes.entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class LotCompleteResp {
    private int id;
    private Product product;
    private Double lotTotalCost;
    private Integer lotQuantityProducts;
    private List<LotDetailResp> lotDetails;
}

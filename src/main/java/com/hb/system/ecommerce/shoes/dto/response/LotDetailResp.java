package com.hb.system.ecommerce.shoes.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hb.system.ecommerce.shoes.entity.Lot;
import com.hb.system.ecommerce.shoes.entity.Material;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class LotDetailResp {
    private int id;
    private String name;
    private Double detPriceUnit;
    private Integer detQuantity;
    private Double detSubTotal;
}

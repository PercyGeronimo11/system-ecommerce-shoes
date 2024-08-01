package com.hb.system.ecommerce.shoes.dto.response;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PromoDetailResp {
    private int id;
    private String proName;
}

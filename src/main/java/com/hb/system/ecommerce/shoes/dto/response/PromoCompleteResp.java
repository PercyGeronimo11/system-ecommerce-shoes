package com.hb.system.ecommerce.shoes.dto.response;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
@Builder
public class PromoCompleteResp {
    private int id;

    private int promPercentage;

    private Date promStartdate;

    private Date promEnddate;

    private String promDescription;

    private String promUrlImage;

    private Boolean promStatus;
    private List<PromoDetailResp> promoDetail;
}

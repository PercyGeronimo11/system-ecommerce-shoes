package com.hb.system.ecommerce.shoes.dto.request;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class PromoRequest {

    private int promPercentage;

    private Date promStartdate;

    private Date promEnddate;
    
    private String promDescription;

    private String promUrlImage;

    private Boolean promStatus;

    private List<PromoDetailRequest> promDetail;

}

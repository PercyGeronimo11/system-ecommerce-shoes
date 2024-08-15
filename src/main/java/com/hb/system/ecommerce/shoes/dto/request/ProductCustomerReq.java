package com.hb.system.ecommerce.shoes.dto.request;

import lombok.Data;

@Data
public class ProductCustomerReq {
    private Integer customer_id;
    private Integer product_id;
    private Integer rating;
    private Integer clicks;
}

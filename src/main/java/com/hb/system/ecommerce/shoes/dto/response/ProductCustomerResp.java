package com.hb.system.ecommerce.shoes.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCustomerResp {
    private Integer customer_id;
    private Integer product_id;
    private Integer rating;
    private Integer clicks;
}

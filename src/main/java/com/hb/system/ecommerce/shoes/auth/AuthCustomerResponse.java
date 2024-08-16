package com.hb.system.ecommerce.shoes.auth;

import com.hb.system.ecommerce.shoes.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthCustomerResponse {
    String token;
    String username;
    String rol;
    Integer customer_id;
    User usuario;
}

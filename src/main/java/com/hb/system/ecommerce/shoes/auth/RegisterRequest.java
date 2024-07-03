package com.hb.system.ecommerce.shoes.auth;

import com.hb.system.ecommerce.shoes.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    String username;
    String email;
    String password;
    boolean status;
    int role;
}

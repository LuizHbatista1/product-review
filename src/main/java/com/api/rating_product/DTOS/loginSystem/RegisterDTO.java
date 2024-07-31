package com.api.rating_product.DTOS.loginSystem;

import com.api.rating_product.domain.user.RoleType;

public record RegisterDTO (String firstName , String email , String password , RoleType role){
}

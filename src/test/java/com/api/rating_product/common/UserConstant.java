package com.api.rating_product.common;

import com.api.rating_product.DTOS.loginSystem.RegisterDTO;
import com.api.rating_product.domain.user.RoleType;
import com.api.rating_product.domain.user.User;

public class UserConstant {

    public static final RegisterDTO REGISTER_DTO = new RegisterDTO("Teste", "Teste@teste.com","123456789", RoleType.COMMON);
    public static final RegisterDTO INVALID_USER = new RegisterDTO("", "","123", RoleType.COMMON);
    public static final User USER =  new User(REGISTER_DTO);


}

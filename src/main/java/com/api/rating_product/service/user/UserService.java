package com.api.rating_product.service.user;

import com.api.rating_product.DTOS.loginSystem.RegisterDTO;
import com.api.rating_product.domain.user.User;

public interface UserService {

    User findUserById(Long id);

    void lengthPasswordCheck(RegisterDTO registerDTO);

}

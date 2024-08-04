package com.api.rating_product.service;

import com.api.rating_product.DTOS.loginSystem.RegisterDTO;
import com.api.rating_product.common.UserConstant;
import com.api.rating_product.domain.user.User;
import com.api.rating_product.infra.exceptions.PasswordCheckException;
import com.api.rating_product.repositories.UserRepository;
import com.api.rating_product.service.auth.AuthService;
import com.api.rating_product.service.user.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.management.RuntimeMBeanException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = AuthService.class)
public class UserTest {

    @Autowired
    private AuthService authService;

    @MockBean
    private UserServiceImpl userService;


    @MockBean
    private UserRepository userRepository;

    @Test
    public void createUser_WithValidDate_ReturnsUser() throws Exception {

        RegisterDTO registerDTO = UserConstant.REGISTER_DTO;
        User newUser = new User(registerDTO);

        when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(newUser);

        User sut = (User) authService.signUp(registerDTO);
        assertThat(sut).isEqualTo(newUser);

    }

    @Test
    public void createUser_WithInvalidDate_ThrowsException(){

        // espera que a senha tenha menos de 8 caracteres para lançar a exception

        RegisterDTO registerDTO = UserConstant.INVALID_USER;
        User user = new User(registerDTO);

        doThrow(PasswordCheckException.class).when(userService).lengthPasswordCheck(registerDTO);

        assertThatThrownBy(() -> authService.signUp(registerDTO))
                .isInstanceOf(PasswordCheckException.class);

    }

}

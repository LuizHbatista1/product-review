package com.api.rating_product.service.auth;

import com.api.rating_product.DTOS.loginSystem.RegisterDTO;
import com.api.rating_product.domain.user.User;
import com.api.rating_product.repositories.UserRepository;
import com.api.rating_product.service.user.UserServiceImpl;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserServiceImpl userService;

    @Autowired
    public AuthService(UserRepository userRepository, UserServiceImpl userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByEmail(username);
        return user;
    }

    public UserDetails signUp(RegisterDTO data) throws Exception {
        if (userRepository.findByEmail(data.email()) != null) {
            throw new InvalidJwtException("User Already Exist!",null ,null);
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.firstName() , data.email() , encryptedPassword , data.role());
        userService.lengthPasswordCheck(data);
        return userRepository.save(newUser);
    }
}

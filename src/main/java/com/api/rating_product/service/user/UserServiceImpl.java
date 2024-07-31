package com.api.rating_product.service.user;

import com.api.rating_product.domain.user.User;
import com.api.rating_product.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findUserById(Long id) {
        return this.userRepository.findById(id).orElseThrow(()-> new RuntimeException());
    }
}

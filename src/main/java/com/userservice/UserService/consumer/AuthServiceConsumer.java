package com.userservice.UserService.consumer;

import com.userservice.UserService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthServiceConsumer {

    private UserRepository userRepository;

    @Autowired
    public AuthServiceConsumer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

}

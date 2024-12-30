package com.userservice.UserService.consumer;

import com.userservice.UserService.entities.UserInfoDto;
import com.userservice.UserService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

public class AuthServiceConsumer {

    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    public AuthServiceConsumer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(UserInfoDto eventData) {
        try{
            // Todo: Make it transactional, to handle idempotency and validate email, phoneNumber etc
            userService.createOrUpdateUser(eventData);
        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println("AuthServiceConsumer: Exception is thrown while consuming kafka event");
        }
    }

}

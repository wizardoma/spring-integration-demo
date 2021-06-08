package com.wizardom.springintegrationdemo.controller;

import com.wizardom.springintegrationdemo.domain.User;
import com.wizardom.springintegrationdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired @Qualifier("registrationRequest")
    private MessageChannel registrationRequestChannel;

    @Autowired private UserService userService;

    @PostMapping("")
    public User save(@ModelAttribute User user){
        Message<User> message = MessageBuilder.withPayload(user).build();
        boolean sent = registrationRequestChannel.send(message);
        return sent? user : new User();
    }

    @PostMapping("{id}")
    public User save(@PathVariable("id") long id, @ModelAttribute User user){
        User user1 = userService.edit(id, user);
        return  user1;
    }

    @GetMapping("")
    public List<User> getAll(){
        return userService.getUsers();
    }


}

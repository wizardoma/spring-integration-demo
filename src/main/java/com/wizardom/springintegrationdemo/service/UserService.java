package com.wizardom.springintegrationdemo.service;

import com.wizardom.springintegrationdemo.domain.User;
import com.wizardom.springintegrationdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @ServiceActivator(inputChannel = "registrationRequest")
    public void save(User user) {
        userRepository.save(user);
    }

    public User edit(long id, User user) {
        User user1 = userRepository.findById(id).orElseThrow();

        return userRepository.save(user1.setName(user.getName()).setPassword(user.getPassword()).setUsername(user.getUsername()));
    }

    public void delete(long id) {
        userRepository.delete(userRepository.findById(id).orElseThrow());
    }
}

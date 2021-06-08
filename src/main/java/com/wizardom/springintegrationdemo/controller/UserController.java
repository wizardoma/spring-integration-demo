package com.wizardom.springintegrationdemo.controller;

import com.wizardom.springintegrationdemo.domain.User;
import com.wizardom.springintegrationdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("")
    public User save(@ModelAttribute User user){
        User user1 = userService.save(user);
        return user1;
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

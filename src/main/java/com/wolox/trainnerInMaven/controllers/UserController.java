package com.wolox.trainnerInMaven.controllers;

import com.wolox.trainnerInMaven.models.User;
import com.wolox.trainnerInMaven.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        return userRepository.findById(id).get();
    }

    @GetMapping("/name/{name}")
    public User findFirstByUsername(@PathVariable String name) {
        User pepe = userRepository.findFirstByUserName(name).get();
        return pepe;
    }
}
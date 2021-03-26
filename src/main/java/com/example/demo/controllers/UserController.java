package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.User;
import com.example.demo.repository.UserRepository;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/allUsers")
    public List<User> userslist() {
        List<User> allUsers= userRepository.findAll();
        return allUsers;
    }
    
    @GetMapping("/userById/{id}")
    public User getUserById(@PathVariable("id") long id) {
        User user = userRepository.findById(id).get();
        return user;
    }

}

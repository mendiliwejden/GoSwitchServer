package com.example.demo.security.services;

import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.models.Home;
import com.example.demo.models.User;

public interface HomeService {


    public User updatClient(User client , @PathVariable("id") String id);


    public Home updatHome(Home home , @PathVariable("id") long id);
}

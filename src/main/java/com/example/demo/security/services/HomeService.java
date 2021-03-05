package com.example.demo.security.services;

import com.example.demo.models.Home;
import com.example.demo.models.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

public interface HomeService {


    public User updatClient(User client , @PathVariable("id") String id);


    public Home updatHome(Home home , @PathVariable("id") long id);
}

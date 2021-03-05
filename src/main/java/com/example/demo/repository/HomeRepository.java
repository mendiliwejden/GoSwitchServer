package com.example.demo.repository;

import com.example.demo.models.FileDB;
import com.example.demo.models.Home;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HomeRepository extends JpaRepository<Home, Long> {
        List<Home> findByPublished(boolean published);
//    List<Home> findByTitleContaining(String title);
}

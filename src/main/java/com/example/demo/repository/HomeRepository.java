package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Home;

public interface HomeRepository extends JpaRepository<Home, Long> {
        List<Home> findByPublished(boolean published);
        List<Home> findByRegionAndPublished(String region, boolean published);
//    List<Home> findByTitleContaining(String title);
}

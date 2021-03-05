package com.example.demo.repository;

import com.example.demo.models.ExchangeRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRequestRepository extends JpaRepository <ExchangeRequest,Long>{
}

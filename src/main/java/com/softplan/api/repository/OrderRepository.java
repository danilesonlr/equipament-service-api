package com.softplan.api.repository;

import com.softplan.api.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByEndDateIsNull();
}


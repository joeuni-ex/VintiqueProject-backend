package com.mysite.finalProject.repository;

import com.mysite.finalProject.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findByUserId(int userId);
}

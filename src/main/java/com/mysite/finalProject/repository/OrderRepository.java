package com.mysite.finalProject.repository;

import com.mysite.finalProject.dto.OrderResponseDto;
import com.mysite.finalProject.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findByUserId(int userId);

}

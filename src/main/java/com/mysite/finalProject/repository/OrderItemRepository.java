package com.mysite.finalProject.repository;


import com.mysite.finalProject.model.Order;
import com.mysite.finalProject.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
    List<OrderItem> findByOrderId(Long id);
}

package com.mysite.finalProject.service;

import com.mysite.finalProject.dto.OrderResponseDto;
import com.mysite.finalProject.model.Order;
import com.mysite.finalProject.model.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderService {
    void createOrder(User user);

    void order(User user);


    //전체 주문 내역 조회
    Page<OrderResponseDto> getAllOrders(int page, int maxPageSize);

    Order orderView(Long id);

    void orderUpdate(Long id, Order order);
}

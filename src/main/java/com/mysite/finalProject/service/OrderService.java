package com.mysite.finalProject.service;

import com.mysite.finalProject.dto.OrderResponseDto;
import com.mysite.finalProject.model.Order;
import com.mysite.finalProject.model.User;

import java.util.List;

public interface OrderService {
    void createOrder(User user);

    void order(User user);


    //전체 주문 내역 조회
    List<OrderResponseDto> getAllOrders();

    Order orderView(Long id);

    void orderUpdate(Long id, Order order);
}

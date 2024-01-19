package com.mysite.finalProject.service;

import com.mysite.finalProject.dto.OrderResponseDto;
import com.mysite.finalProject.dto.OrderViewResponseDto;
import com.mysite.finalProject.model.Order;
import com.mysite.finalProject.model.OrderItem;
import com.mysite.finalProject.model.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderService {
    void createOrder(User user);


    //주문 하기
    Long order(User user);

    //전체 주문 내역 조회
    Page<OrderResponseDto> getAllOrders(int page, int maxPageSize);


    //유저별 주문 내역 조회(페이징 추가)
    Page<OrderResponseDto> getUserOrder(int page, int maxPageSize, User user);

    //유저 주문 상세 내용
    List<OrderViewResponseDto> orderView(Long id);


    //수정
    void orderUpdate(Long id, Order order);
}

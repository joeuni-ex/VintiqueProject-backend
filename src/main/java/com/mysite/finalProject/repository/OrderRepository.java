package com.mysite.finalProject.repository;

import com.mysite.finalProject.dto.OrderResponseDto;
import com.mysite.finalProject.model.Order;
import com.mysite.finalProject.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findByUserId(int userId);


    //전체 제품 조회 페이지네이션 적용
    Page<Order> findAll (Pageable pageable);}

package com.mysite.finalProject.dto;


import com.mysite.finalProject.model.Order;
import com.mysite.finalProject.model.OrderItem;
import com.mysite.finalProject.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDto {

    private Long id; // 주문 번호
    private String status ;// 상태
    private int totalPrice ;//총 금액
    private List<OrderItem> orderItems;// 주문한제품
    private LocalDateTime createDate;//주문일자



    public static OrderResponseDto toDto(Order order) {
        return new OrderResponseDto(
      order.getId(),order.getStatus(),order.getTotalPrice(),order.getOrder_items(),order.getCreateDate());}
}

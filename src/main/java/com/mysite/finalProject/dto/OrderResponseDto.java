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
//    private List<OrderItem> orderItems;// 주문한제품
    private String createDate;//주문일자
    private Long userId; //유저 아이디
    private String userName; //유저 이름
    private int cartItemCount; //주문 시 카트안의 아이템 개수


    public static OrderResponseDto toDto(Order order) {
        return new OrderResponseDto(
      order.getId(),order.getStatus(),order.getTotalPrice(),order.getCreateDate(),order.getUser().getId(),order.getUser().getName(), order.getCartItemCount());}
}

package com.mysite.finalProject.dto;

import com.mysite.finalProject.model.CartItem;
import com.mysite.finalProject.model.Order;
import com.mysite.finalProject.model.OrderItem;
import com.mysite.finalProject.model.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderViewResponseDto {

    private Long id;
    private Long productId; //제품 id
    private String name; //제품 이름
    private int quantity; // 개수
    private int price; // 금액
    private String mainImage; //메인 이미지

    public static OrderViewResponseDto toDto(OrderItem orderItem, Long productId, String name, int price ,String mainImage) {
        return new OrderViewResponseDto(orderItem.getId(),productId ,name, orderItem.getQuantity(),price,mainImage);
    }

}

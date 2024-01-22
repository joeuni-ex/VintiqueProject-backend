package com.mysite.finalProject.dto;

import com.mysite.finalProject.model.OrderItem;
import com.mysite.finalProject.model.Product;
import com.mysite.finalProject.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InterestResponseDto {

    private Long userId;
    private Long productId;
    private String name; //제품 이름
    private int price; // 금액
    private String mainImage; //메인 이미지


    public static InterestResponseDto toDto(User user,Product product) {
        return new InterestResponseDto(user.getId(), product.getId(), product.getName(), product.getPrice(), product.getMainImage());
    }

}

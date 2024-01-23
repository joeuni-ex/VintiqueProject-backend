package com.mysite.finalProject.dto;

import com.mysite.finalProject.model.Product;
import com.mysite.finalProject.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequestDto {

    private Long orderItemId;
    private String reviewContent;
    private Integer rate;
    private Long productId;


}

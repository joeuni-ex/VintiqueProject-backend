package com.mysite.finalProject.model;

import lombok.*;

// NaverResult의 item에 들어감
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Product {
    private String title;
    private String link;
    private String image;
    private String productId;
    private String productType;
    private String maker;
    private String brand;
    private String category1;
    private String category2;
    private String category3;
    private String category4;
}

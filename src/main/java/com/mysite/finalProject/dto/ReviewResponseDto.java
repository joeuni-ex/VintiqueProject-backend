package com.mysite.finalProject.dto;

import com.mysite.finalProject.model.Product;
import com.mysite.finalProject.model.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponseDto {

    private Long id;//리뷰id
    private Long productId;//제품id
    private String name; //제품 이름
    private String mainImage; //메인 이미지
    private String reviewContent;//리뷰 내용
    private Integer rate;//별점
    private String createTime;//리뷰 작성일자


    public static ReviewResponseDto toDto(Product product, Review review) {
        return new ReviewResponseDto(  review.getId(), product.getId(), product.getName(), product.getMainImage(),review.getReviewContent(),review.getRate(),review.getCreateTime());
    }

}

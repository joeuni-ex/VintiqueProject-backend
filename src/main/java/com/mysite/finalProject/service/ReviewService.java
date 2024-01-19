package com.mysite.finalProject.service;

import com.mysite.finalProject.dto.ReviewRequestDto;
import com.mysite.finalProject.model.User;
import com.mysite.finalProject.repository.projection.ReviewItem;

import java.util.List;

public interface ReviewService {
    //리뷰 작성하기
    void create(ReviewRequestDto req, User User);

    //제품 별 리뷰 조회
    List<ReviewItem> getReviewsByProductId(Long productId);

    //본인 작성 여부 확인
    String getReviewWriter(Long reviewId);

    void deleteById(User user, Long reviewId);
}

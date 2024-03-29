package com.mysite.finalProject.service;

import com.mysite.finalProject.dto.ReviewRequestDto;
import com.mysite.finalProject.dto.ReviewResponseDto;
import com.mysite.finalProject.model.Review;
import com.mysite.finalProject.model.User;
import com.mysite.finalProject.repository.projection.ReviewItem;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    //리뷰 작성하기
    void create(ReviewRequestDto req, User User);


    //리뷰 상세 조회
    ReviewResponseDto getReviewById(Long reviewId);

    //제품 별 리뷰 조회
    List<ReviewItem> getReviewsByProductId(Long productId);


    //유저 별 리뷰 조회
    Page<ReviewResponseDto> getReviewsByUserID(int page, int maxPageSize, User user);

    //본인 작성 여부 확인
    String getReviewWriter(Long reviewId);

    //리뷰 수정하기
    void modify(ReviewRequestDto req, Long reviewId);

    void deleteById(User user, Long reviewId);
}

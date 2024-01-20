package com.mysite.finalProject.service;

import com.mysite.finalProject.dto.ReviewRequestDto;
import com.mysite.finalProject.model.Review;
import com.mysite.finalProject.model.User;
import com.mysite.finalProject.repository.OrderItemRepository;
import com.mysite.finalProject.repository.ReviewRepository;
import com.mysite.finalProject.repository.UserRepository;
import com.mysite.finalProject.repository.projection.ReviewItem;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewServiceImpl implements ReviewService{


    private final ReviewRepository reviewRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserRepository userRepository;


    @Override
    //리뷰 작성하기
    public void create(ReviewRequestDto req, User user){
        Review review =  Review.createReview(user,req);
        reviewRepository.save(review);
    }

    //제품 별 리뷰 조회
    @Override
    public List<ReviewItem> getReviewsByProductId(Long productId) {
      return reviewRepository.findByReviewProductId(productId);
    }

    @Override
    //유저 별 리뷰 조회
    public List<ReviewItem> getReviewsByUserID(User user) {
        return reviewRepository.findReviewByUserId(user.getId());
    }



    @Override
    //본인 작성 여부 확인
    public String getReviewWriter(Long reviewId){
        return reviewRepository.findUsernameById(reviewId);
    }

    //제품 삭제 -> 댓글 작성한 유저만 가능
    @Override
    public void deleteById(User user, Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }



}

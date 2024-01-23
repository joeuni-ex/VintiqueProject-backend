package com.mysite.finalProject.service;

import com.mysite.finalProject.dto.ReviewRequestDto;
import com.mysite.finalProject.dto.ReviewResponseDto;
import com.mysite.finalProject.model.Product;
import com.mysite.finalProject.model.Review;
import com.mysite.finalProject.model.User;
import com.mysite.finalProject.repository.OrderItemRepository;
import com.mysite.finalProject.repository.ReviewRepository;
import com.mysite.finalProject.repository.UserRepository;
import com.mysite.finalProject.repository.projection.ReviewItem;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public Page<ReviewResponseDto> getReviewsByUserID(int page, int maxPageSize, User user) {
        Pageable pageable = PageRequest.of(page, maxPageSize); //maxPageSize -> 한 페이지에 출력할 게시글 개수

        Page<Review> reviews = reviewRepository.findByUserId(user.getId(),pageable);

        List<ReviewResponseDto> result = new ArrayList<>();

        for(Review review : reviews) {
            Product product = review.getProduct();
            result.add(new ReviewResponseDto().toDto(product,review));
        }
        return new PageImpl<>(result, pageable, reviews.getTotalElements());
    }

    @Override
    //본인 작성 여부 확인
    public String getReviewWriter(Long reviewId){
        return reviewRepository.findUsernameById(reviewId);
    }



    @Override
    //리뷰 수정하기
    public void modify(ReviewRequestDto req, Long reviewId  ){
        // 일단 id에 맞는 값들을 가져옴
        Optional<Review> review = reviewRepository.findById(reviewId);

        review.ifPresent(t->{

                    if(req.getReviewContent() !=null){
                        t.setReviewContent(req.getReviewContent());
                    }
                    if(req.getRate() !=null){
                        t.setRate(req.getRate());
                    }
                    this.reviewRepository.save(t);
                }
        );
    }
    
    
    //리뷰 삭제 -> 댓글 작성한 유저만 가능
    @Override
    public void deleteById(User user, Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }



}

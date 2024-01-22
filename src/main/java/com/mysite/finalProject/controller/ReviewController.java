package com.mysite.finalProject.controller;

import com.mysite.finalProject.dto.CartCreateRequestDto;
import com.mysite.finalProject.dto.ReviewRequestDto;
import com.mysite.finalProject.model.Review;
import com.mysite.finalProject.model.User;
import com.mysite.finalProject.repository.UserRepository;
import com.mysite.finalProject.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@RequestMapping("api/review")
@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final UserRepository userRepository;

    // 리뷰 저장하기
    @PostMapping
    public ResponseEntity<Object> create(@RequestBody ReviewRequestDto req ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName()).orElseThrow();
        reviewService.create(req,user);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //제품 별 리뷰 조회
    @GetMapping("{productId}")
    public ResponseEntity<Object> getReviewsByProductId(@PathVariable Long productId) {
        return new ResponseEntity<>(  reviewService.getReviewsByProductId(productId),HttpStatus.OK);
    }

    //유저 별 리뷰 조회
    @GetMapping
    public ResponseEntity<Object> getReviewsByUserId(@RequestParam(value = "page",defaultValue = "0") int page , @RequestParam(value = "maxpage",defaultValue = "5") int maxPageSize) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName()).orElseThrow();
        return new ResponseEntity<>(  reviewService.getReviewsByUserID(page,maxPageSize,user),HttpStatus.OK);
    }

    //리뷰 삭제(본인이 작성한 리뷰만 가능)
    @DeleteMapping("{reviewId}")
    public ResponseEntity<Object> deleteById(@PathVariable Long reviewId ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName()).orElseThrow();

        String result = reviewService.getReviewWriter(reviewId);
        //리뷰를 작성한 유저와 현재 로그인 되어있는 유저가 다를 경우 에러 발생
        if(!Objects.equals(user.getUsername(), result)){
            return new ResponseEntity<>(  HttpStatus.CONFLICT);
        }
        else{
            reviewService.deleteById(user,reviewId);
        }
        return new ResponseEntity<>( HttpStatus.OK);
    }
}

package com.mysite.finalProject.controller;

import com.mysite.finalProject.dto.ReviewRequestDto;
import com.mysite.finalProject.model.User;
import com.mysite.finalProject.repository.UserRepository;
import com.mysite.finalProject.service.InterestService;
import com.mysite.finalProject.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/interest")
@RestController
@RequiredArgsConstructor
public class InterestController {

    private final UserRepository userRepository;
    private final InterestService interestService;


    //관심 제품 추가
    @PostMapping("{productId}")
    public ResponseEntity<Object> create(@PathVariable Long productId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName()).orElseThrow();
        interestService.addInterest(productId,user);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //관심 제품 조회
    @GetMapping
    public ResponseEntity<Object> getCategoryByOrderDesc(@RequestParam(value = "page",defaultValue = "0") int page,
                                                         @RequestParam(value = "maxpage",defaultValue = "5") int maxPageSize){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName()).orElseThrow();
        return new ResponseEntity<>(interestService.getInterestByUser(page,maxPageSize,user), HttpStatus.OK);
    }

    //관심 제품 삭제
    @DeleteMapping("{productId}")
    public ResponseEntity<Object> deleteProduct(@PathVariable  Long productId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName()).orElseThrow();
        interestService.deleteInterestByUserId(productId,user.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }



}

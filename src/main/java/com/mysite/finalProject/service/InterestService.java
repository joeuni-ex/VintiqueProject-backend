package com.mysite.finalProject.service;

import com.mysite.finalProject.dto.InterestResponseDto;
import com.mysite.finalProject.model.Product;
import com.mysite.finalProject.model.User;
import org.springframework.data.domain.Page;

public interface InterestService {


    //유저별 관심 제품 내역 조회(페이징 추가)
    Page<InterestResponseDto> getInterestByUser(int page, int maxPageSize, User user);

    //관심 제품 작성하기
    void addInterest(Long productId, User user);

    //관심 제품 삭제하기
    void deleteInterestByUserId(Long productId, Long userId);
}

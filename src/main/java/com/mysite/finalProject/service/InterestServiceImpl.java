package com.mysite.finalProject.service;

import com.mysite.finalProject.dto.InterestResponseDto;
import com.mysite.finalProject.model.*;
import com.mysite.finalProject.repository.InterestRepository;
import com.mysite.finalProject.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class InterestServiceImpl implements InterestService{

    private final InterestRepository interestRepository;
    private final ProductRepository productRepository;

    @Override
    //관심 제품 추가하기
    public void addInterest(Long productId, User user){
        Product product =  productRepository.findById(productId).get();
        //제품의 interestCount 업데이트(+1)
        product.setInterestCount(product.getInterestCount() + 1);
        productRepository.save(product);


        Interest interest =  Interest.addInterest(user,product,productId);
        interestRepository.save(interest);
    }


    //유저별 관심 제품 내역 조회(페이징 추가)
    @Override
    public Page<InterestResponseDto> getInterestByUser(int page, int maxPageSize, User user) {
        Pageable pageable = PageRequest.of(page, maxPageSize); //maxPageSize -> 한 페이지에 출력할 게시글 개수
        Page<Interest> interests = interestRepository.findByUserId(user.getId(),pageable);

        List<InterestResponseDto> result = new ArrayList<>();

        for(Interest item : interests) {
            Product product = item.getProduct();
            result.add(new InterestResponseDto().toDto(user,product,item.getCreateTime()));
        }
        return new PageImpl<>(result, pageable, interests.getTotalElements());
    }

    //관심 제품 삭제하기
    @Override
    public void deleteInterestByUserId(Long productId, Long userId){
        //제품 Interest count -1
        Product product =  productRepository.findById(productId).get();
        product.setInterestCount(product.getInterestCount() - 1);
        productRepository.save(product);

        interestRepository.deleteByUserIdAndProductId(userId,productId);
    }
}

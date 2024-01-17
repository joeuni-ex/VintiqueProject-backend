package com.mysite.finalProject.service;


import com.mysite.finalProject.model.Purchase;
import com.mysite.finalProject.repository.PurchaseRepository;
import com.mysite.finalProject.repository.projection.PurchaseItem;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;


    //구매 추가
    @Override
    public Purchase savePurchase(Purchase purchase){
        purchase.setPurchaseTime(LocalDateTime.now());
        return purchaseRepository.save(purchase);
    }


    //유저별 모든 구매내역 가져오기 (페이징 추가)
    @Override
    public Page<PurchaseItem> findPurchaseItemsOfUser(int page, int maxPageSize, Long userId){
        Pageable pageable = PageRequest.of(page, maxPageSize);  //maxPageSize -> 한 페이지에 출력할 게시글 개수
        return purchaseRepository.findAllPurchasesOfUser(pageable,userId);
    }


    @Override
    //모든 구매내역 가져오기
    public Page<PurchaseItem> findAllPurchase(int page, int maxPageSize){
        Pageable pageable = PageRequest.of(page, maxPageSize);  //maxPageSize -> 한 페이지에 출력할 게시글 개수
        return purchaseRepository.findAllPurchase(pageable);
    }


}

package com.mysite.finalProject.service;


import com.mysite.finalProject.model.Purchase;
import com.mysite.finalProject.repository.PurchaseRepository;
import com.mysite.finalProject.repository.projection.PurchaseItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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


    //유저별 모든 구매내역 가져오기
    @Override
    public List<PurchaseItem> findPurchaseItemsOfUser(Long userId){
        return purchaseRepository.findAllPurchasesOfUser(userId);
    }


    @Override
    //모든 구매내역 가져오기
    public List<PurchaseItem> findAllPurchase(){
        return purchaseRepository.findAllPurchase();
    }


}

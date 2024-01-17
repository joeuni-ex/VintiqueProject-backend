package com.mysite.finalProject.service;

import com.mysite.finalProject.model.Purchase;
import com.mysite.finalProject.repository.projection.PurchaseItem;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PurchaseService {
    //구매 추가
    Purchase savePurchase(Purchase purchase);

    //유저별 모든 구매내역 가져오기
    Page<PurchaseItem> findPurchaseItemsOfUser(int page, int maxPageSize, Long userId);

    //모든 구매내역 가져오기
    Page<PurchaseItem> findAllPurchase(int page, int maxPageSize);
}

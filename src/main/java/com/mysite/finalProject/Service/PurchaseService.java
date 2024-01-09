package com.mysite.finalProject.Service;

import com.mysite.finalProject.model.Purchase;
import com.mysite.finalProject.repository.projection.PurchaseItem;

import java.util.List;

public interface PurchaseService {
    //구매 추가
    Purchase savePurchase(Purchase purchase);

    //모든 구매내역 가져오기
    List<PurchaseItem> findPurchaseItemsOfUser(Long userId);

    //모든 구매내역 가져오기 (유저별)
    List<PurchaseItem> findPurchaseItems();
}

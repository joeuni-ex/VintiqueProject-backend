package com.mysite.finalProject.repository;

import com.mysite.finalProject.model.Purchase;
import com.mysite.finalProject.repository.projection.PurchaseItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase,Long> {

    //유저의 구매 제품 모두를 조회함 .
    //가져올 때는 프로젝션을 사용해서 데이터(제품 이름,가격,구매시간) 가져옴
    @Query("select " +
            "prd.name as name, pur.price as price, pur.purchaseTime as purchaseTime " +
            "from Purchase pur left join Product prd on prd.id = pur.productId " +
            "where pur.userId = :userId" )
    List<PurchaseItem> findAllPurchasesOfUser(@Param("userId") Long userId);


    //모든 구매 제품 조회 함 - 관리자용
    @Query("select " +
            "prd.name as name, pur.price as price, pur.purchaseTime as purchaseTime " +
            "from Purchase pur left join Product prd on prd.id = pur.productId "  )
    List<PurchaseItem> findAllPurchases();
}

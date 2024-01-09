package com.mysite.finalProject.repository.projection;

import java.time.LocalDateTime;

public interface PurchaseItem {
    //프로젝션 (Projection) 프로젝션이란 SELECT 절에서 조회할 대상을 지정하는 것
    //여기서 제품의 이름,가격,구매시간 만을 가져오는 프로젝션을 만들었다.
    String getName();
    Integer getPrice();
    LocalDateTime getPurchaseTime();

}

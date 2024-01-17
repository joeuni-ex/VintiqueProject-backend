package com.mysite.finalProject.repository.projection;

import java.time.LocalDateTime;

public interface PurchaseItem {
    //프로젝션 (Projection) 프로젝션이란 SELECT 절에서 조회할 대상을 지정하는 것
    Long getUserId();
    Long getProductId();
    String getName();
    Integer getPrice();
    String getMainImage();
    LocalDateTime getPurchaseTime();

}

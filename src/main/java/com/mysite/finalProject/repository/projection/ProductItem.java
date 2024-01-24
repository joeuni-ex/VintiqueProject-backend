package com.mysite.finalProject.repository.projection;

import java.util.List;

public interface ProductItem {
    //게시글 상세 보기 시 필요한 데이터
     Long getId();
     String getName();
     String getDescription();
     String getCategory();
     Integer getPrice();
     Integer getStock();
     String getMainImage();
     List<String> getBoardImageList();
     int getInterestCount();
     String getCreateTime();
}

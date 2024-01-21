package com.mysite.finalProject.dto;


import jakarta.persistence.Column;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetProductResponseDto {

    //게시글 상세 보기 시 필요한 데이터
    private Long productId;
    private String name;
    private String description;
    private String category;
    private Integer price;
    private Integer stock;
    private String mainImage;
    private List<String> boardImageList;
    private int favoriteCount;
    private String createTime;


    public static ResponseEntity<GetProductResponseDto> success(){
        GetProductResponseDto result = new GetProductResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


}

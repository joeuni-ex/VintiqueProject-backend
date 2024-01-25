package com.mysite.finalProject.dto;

import com.mysite.finalProject.model.Product;
import com.mysite.finalProject.repository.projection.ProductItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailResponseDto {
    //제품 상세 정보
    private Long id;
    private String name;
    private String description;
    private String category;
    private Integer price;
    private Integer stock;
    private String mainImage;
    private List<String> boardImageList;
    private Integer interestCount;
    private String createTime;
    private boolean addedInterest;//관심 제품 추가 여부


    public static ProductDetailResponseDto toDto( ProductItem product, Boolean addedInterest) {
        return new ProductDetailResponseDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getCategory(),
                product.getPrice(),
                product.getStock(),
                product.getMainImage(),
                product.getBoardImageList(),
                product.getInterestCount(),
                product.getCreateTime(),
                addedInterest);
    }

}

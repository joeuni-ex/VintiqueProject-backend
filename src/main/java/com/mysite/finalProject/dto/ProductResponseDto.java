package com.mysite.finalProject.dto;


import com.mysite.finalProject.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {

    private Long id;
    private String name;
    private String category;
    private String description;
    private Integer price;
    private Integer stock;
    private String mainImage;
    private Integer interestCount;
    private String createTime;
    private boolean addedInterest;//관심 제품 추가 여부

    public static ProductResponseDto toDto( Product product, Boolean addedInterest) {
        return new ProductResponseDto( product.getId(),
                product.getName(),
                product.getCategory(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getMainImage(),
                product.getInterestCount(),
                product.getCreateTime(),
                addedInterest);
    }

}

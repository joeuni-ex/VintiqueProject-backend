package com.mysite.finalProject.repository;


import com.mysite.finalProject.dto.PostProductRequestDto;
import com.mysite.finalProject.model.Product;
import com.mysite.finalProject.repository.projection.ProductItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("select " +
            "prd.id as id," +
            "prd.name as name, " +
            "prd.price as price, " +
            "prd.description as description, " +
            "prd.mainImage as mainImage, " +
            "prd.category as category, " +
            "prd.stock as stock, " +
            "prd.favoriteCount as favoriteCount, " +
            "prd.reviewCount as reviewCount, " +
            "img.image as boardImageList, " +
            "prd.createTime as createTime " +
            "from product prd left join image img on prd.id = img.productId " +
            "where img.productId = :productId" )
    List<ProductItem> findByIdImageOfProduct(@Param("productId") Long productId);

    Page<Product> findAll (Pageable pageable);
}

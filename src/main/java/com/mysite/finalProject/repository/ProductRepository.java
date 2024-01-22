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
            "img.image as boardImageList, " +
            "prd.createTime as createTime " +
            "from Product prd left join Image img on prd.id = img.productId " +
            "where img.productId = :productId" )
    List<ProductItem> findByIdImageOfProduct(@Param("productId") Long productId);

    //전체 제품 조회 페이지네이션 적용
    Page<Product> findAll (Pageable pageable);

    //전체 제품 조회 가격 높은 순 , 페이지네이션 적용
    Page<Product> findAllByOrderByPriceDesc (Pageable pageable);

    //전체 제품 조회 가격 낮은 순 , 페이지네이션 적용
    Page<Product> findAllByOrderByPriceAsc (Pageable pageable);

    //카테고리 별 조회 테스트
    @Query("select p from Product p where p.category = :category ORDER BY p.price ASC")
    Page<Product> findByCategoryPriceAsc (@Param("category") String category ,Pageable pageable);

    //카테고리 별 조회 가격 높은 순, 페이지네이션 적용
    @Query("select p from Product p where p.category = :category ORDER BY p.price DESC")
    Page<Product> findByCategoryPriceDesc (@Param("category") String category ,Pageable pageable);


    //카테고리 별 조회 가격 높은 순, 페이지네이션 적용
    Page<Product> findByCategory(Pageable pageable, String category);


}

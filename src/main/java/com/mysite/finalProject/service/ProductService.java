package com.mysite.finalProject.service;

import com.mysite.finalProject.dto.PostProductRequestDto;
import com.mysite.finalProject.model.Product;
import com.mysite.finalProject.repository.projection.ProductItem;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    //제품 저장하기
    Product saveProduct(PostProductRequestDto dto);

    //제품 수정하기
    Optional<Product> modifyProduct(Long id, PostProductRequestDto dto);

    //제품 삭제하기
    void deleteProduct(Long id);

    //모든 제품 조회하기(페이징 처리) -> 게시판 용
    Page<Product> findAll(int page, int maxPageSize);

    //모든 제품 조회하기(가격 높은 순 처리)
    Page<Product> findAllOrderByColumnDesc(int page, int maxPageSize);

    //모든 제품 조회하기(가격 낮은 순 처리)
    Page<Product> findAllOrderByColumnAsc(int page, int maxPageSize);

    //카테고리 별 제품 조회하기(페이징 처리)
    Page<Product> findByCategory(int page, int maxPageSize, String category);

    //제품 상세 조회하기
    List<ProductItem> findByIdProduct(Long id);
}

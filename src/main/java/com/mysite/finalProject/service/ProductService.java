package com.mysite.finalProject.service;

import com.mysite.finalProject.dto.PostProductRequestDto;
import com.mysite.finalProject.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    //제품 저장하기
    Product saveProduct(PostProductRequestDto dto);

    //제품 삭제하기
    void deleteProduct(Long id);

    //모든 제품 조회하기
    List<Product> findAllProducts();

    //제품 상세 조회하기
    Optional<Product> findByIdProduct(Long id);
}

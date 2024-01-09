package com.mysite.finalProject.Service;

import com.mysite.finalProject.model.Product;

import java.util.List;

public interface ProductService {
    //제품 저장하기
    Product saveProduct(Product product);

    //제품 삭제하기
    void deleteProduct(Long id);

    //모든 제품 조회하기
    List<Product> findeAllProducts();
}

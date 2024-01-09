package com.mysite.finalProject.Service;

import com.mysite.finalProject.model.Product;
import com.mysite.finalProject.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    //제품 저장하기
    @Override
    public Product saveProduct(Product product){
        product.setCreateTime(LocalDateTime.now());
        return productRepository.save(product);
    }

    //제품 삭제하기
    @Override
    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

    //모든 제품 조회하기
    @Override
    public List<Product> findeAllProducts(){
        return productRepository.findAll();
    }

}
